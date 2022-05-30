package com.example.schedule.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.schedule.R
import com.example.schedule.databinding.FragmentDayOfWeekBinding
import com.example.schedule.databinding.FragmentGroupSelectionBinding
import com.example.schedule.scheduleParser.Parser

class DayOfWeekFragment : Fragment() {
    private var _binding: FragmentDayOfWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDayOfWeekBinding.inflate(inflater,container,false)
        //setting up group choices
        val groupNames = resources.getStringArray(R.array.Дни_недели)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item,groupNames)
        //onlicklistener
        binding.mondayButton.setOnClickListener() {
            arguments?.putString("day", "1")
            //Log.e("error", parser.arrayListGroup[0])
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, extras)
        }

        binding.tuesdayButton.setOnClickListener() {
            arguments?.putString("day", "2")
            val extras = FragmentNavigatorExtras(binding.tuesdayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, extras)
        }

        binding.wednesdayButton.setOnClickListener() {
            arguments?.putString("day", "3")
            val extras = FragmentNavigatorExtras(binding.wednesdayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, extras)
        }

        binding.thursdayButton.setOnClickListener() {
            arguments?.putString("day", "4")
            val extras = FragmentNavigatorExtras(binding.thursdayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, extras)
        }

        binding.fridayButton.setOnClickListener() {
            arguments?.putString("day", "5")
            val extras = FragmentNavigatorExtras(binding.fridayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, extras)
        }

        binding.saturdayButton.setOnClickListener() {
            arguments?.putString("day", "6")
            val extras = FragmentNavigatorExtras(binding.saturdayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, extras)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        sharedElementEnterTransition = animation
        sharedElementEnterTransition = animation
    }

}