package com.example.schedule.fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.schedule.R
import com.example.schedule.databinding.FragmentGroupSelectionBinding
import com.example.schedule.databinding.FragmentRegistrationBinding
import com.example.schedule.scheduleParser.Parser
import org.json.JSONArray


class GroupSelectionFragment : Fragment() {
    private var _binding: FragmentGroupSelectionBinding? = null
    private val binding get() = _binding!!
    private val groupRegex = "(11-1)([0-9]{2})"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGroupSelectionBinding.inflate(inflater,container,false)

        //setting up group choices
        val groupNames = resources.getStringArray(R.array.Название_Группы)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item,groupNames)
        binding.GroupsSelectionTextView.setAdapter(arrayAdapter)
        arrayAdapter.notifyDataSetChanged()
        (activity as AppCompatActivity).supportActionBar?.title =
           "Выбери группу"
        val bundle = Bundle()
        bundle.putString("name",arguments?.getString("name"))
        val sp = context?.getSharedPreferences("PreferencesData", Context.MODE_PRIVATE)
        val editor = sp?.edit()

        //onlicklistener
        binding.groupSaveButton.setOnClickListener {
            editor?.putString("group", getRegexResult(binding.GroupsSelectionTextView.text.toString(),groupRegex))?.apply()
            bundle.putString("group", getRegexResult(binding.GroupsSelectionTextView.text.toString(),groupRegex))
            Navigation.findNavController(binding.root).navigate(R.id.action_groupSelectionFragment_to_dayOfWeekFragment, bundle,null, null)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
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