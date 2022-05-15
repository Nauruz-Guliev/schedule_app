package com.example.schedule.fragments

import android.os.Bundle
import android.transition.TransitionInflater
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
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, null,null, extras)
        }

        binding.tuesdayButton.setOnClickListener() {
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, null,null, extras)
        }

        binding.wednesdayButton.setOnClickListener() {
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, null,null, extras)
        }

        binding.thursdayButton.setOnClickListener() {
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, null,null, extras)
        }

        binding.fridayButton.setOnClickListener() {
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, null,null, extras)
        }

        binding.saturdayButton.setOnClickListener() {
            val extras = FragmentNavigatorExtras(binding.mondayButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, null,null, extras)
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