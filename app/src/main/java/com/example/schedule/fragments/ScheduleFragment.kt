package com.example.schedule.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.adapters.CustomRecyclerAdapter
import com.example.schedule.dataBase.GroupsViewModel
import com.example.schedule.databinding.FragmentScheduleBinding
import com.example.schedule.scheduleParser.GroupsConverter
import org.json.JSONArray


class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var mGroupsViewModel: GroupsViewModel
    private lateinit var names: List<String>
    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var roomRegex = "(в[\\s]{1,5}|ауд.[\\s]{0,2})([А-Я0-9]{2,7})"
    private var nameRegex = "()([а-яА-Я\\s.a-zA-Z-0-9]+)"
    private var times = listOf<String>("8:30\n10:00", "10:10\n11:40", "11:50\n13:20", "14:00\n15:30", "15:40\n17:10", "17:50\n19:20", "20:00\n21:30")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        // просто имена - пустышки
        if (arguments?.getString("name") != null && arguments?.getString("name") != "") {
            (activity as AppCompatActivity).supportActionBar?.title = arguments?.getString("name") + ", расписание на " +
                arguments?.getString("weekDay")
        } else {
            (activity as AppCompatActivity).supportActionBar?.title =
                arguments?.getString("weekDay")
        }
        var day = arguments?.getString("day")
        var group = arguments?.getString("group")
        val groupsConverter:GroupsConverter = GroupsConverter()
        mGroupsViewModel = ViewModelProvider(this).get(GroupsViewModel::class.java)

        mGroupsViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            schGroup ->
            var array: Array<String?> = arrayOfNulls<String>(8)
            var i = 0
            for (item in schGroup) {
                array[i] = item.groups
                i++
            }
            val jArray = JSONArray(array[Integer.parseInt(day.toString())])
            var listOfLessons = mutableListOf<String>()
            var listOfRooms = mutableListOf<String>()
            var timesList = mutableListOf<String>()
            for (i in 0..jArray.length() - 1) {
                var jArr = JSONArray(jArray[i].toString())
                if (jArr[Integer.parseInt(group.toString())-1].toString() != "") {
                    var result = jArr[Integer.parseInt(group.toString())-1].toString()
                    listOfLessons.add(result.substringBefore("\n"))
                    listOfRooms.add(getRegexResult(result, roomRegex))
                    timesList.add(times[i])
                }
            }
            names = listOfLessons
            // всегда используйте binding и в других фрагментах
            binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
            binding.recyclerView.adapter = CustomRecyclerAdapter(names, listOfRooms, timesList)


        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    fun getRegexResult(text:String, pattern:String) : String{
        var resp : String = ""

        val matches = pattern.toRegex().findAll(text)

        matches.forEach { m ->
            val v = m.value
            val idx = m.range
            resp = m.groupValues[2]
        }
        return resp
    }

}