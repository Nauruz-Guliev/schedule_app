package com.example.schedule.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schedule.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import com.example.schedule.adapters.CustomRecyclerAdapter
import com.example.schedule.databinding.FragmentRegistrationBinding
import com.example.schedule.databinding.FragmentScheduleBinding
import com.example.schedule.scheduleParser.Parser
import org.json.JSONArray


class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var names: List<String>
    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var roomRegex = "(в[\\s]{1,5}|ауд.[\\s]{0,2})([А-Я0-9]{2,7})"
    private var nameRegex = "()([а-яА-Я\\s.a-zA-Z-0-9]+)"
    private var times = listOf<String>("8:30\n10:00", "10:10\n11:40", "11:50\n13:20", "14:00\n15:30", "15:40\n17:10", "17:50\n19:20")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        // просто имена - пустышки
        var parser = arguments?.getSerializable("parser") as Parser
        var day = arguments?.getString("day")
        var group = arguments?.getString("group")
        var jArray = JSONArray(parser.daysArray[Integer.parseInt(day.toString())])
        var listOfLessons = mutableListOf<String>()
        var listOfRooms = mutableListOf<String>()
        var timesList = mutableListOf<String>()
        for (i in 0..jArray.length() - 1) {
            //Log.e("hhh", jArray[i].toString())
            var jArr = JSONArray(jArray[i].toString())
            jArr[Integer.parseInt(group.toString())]


            if (jArr[Integer.parseInt(group.toString())].toString() != "") {
                var result = jArr[Integer.parseInt(group.toString())].toString()
                listOfLessons.add(result.substringBefore("\n"))
                listOfRooms.add(getRegexResult(result, roomRegex))
                timesList.add(times[i])
                Log.e("hhh", result)
            }
        }
        names = listOfLessons
        // всегда используйте binding и в других фрагментах
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = CustomRecyclerAdapter(names, listOfLessons, timesList)

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