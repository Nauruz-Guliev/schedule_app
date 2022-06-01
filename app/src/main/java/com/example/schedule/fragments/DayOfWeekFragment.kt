package com.example.schedule.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
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
        animateButtons()
        (activity as AppCompatActivity).supportActionBar?.title =
            "Выбери день недели"
        //val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item,groupNames)
        //onlicklistener
        binding.mondayButton.setOnClickListener() {
            arguments?.putString("day", "1")
            arguments?.putString("weekDay", "понедельник")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, null)
        }

        binding.tuesdayButton.setOnClickListener() {
            arguments?.putString("day", "2")
            arguments?.putString("weekDay", "вторник")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, null)
        }

        binding.wednesdayButton.setOnClickListener() {
            arguments?.putString("day", "3")
            arguments?.putString("weekDay", "среда")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, null)
        }

        binding.thursdayButton.setOnClickListener() {
            arguments?.putString("day", "4")
            arguments?.putString("weekDay", "четверг")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, null)
        }

        binding.fridayButton.setOnClickListener() {
            arguments?.putString("day", "5")
            arguments?.putString("weekDay", "пятница")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, null)
        }

        binding.saturdayButton.setOnClickListener() {
            arguments?.putString("day", "6")
            arguments?.putString("weekDay", "суббота")
            Navigation.findNavController(binding.root).navigate(R.id.action_dayOfWeekFragment_to_scheduleFragment, arguments,null, null)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        sharedElementEnterTransition = animation
    }
    private fun animateButtons(){
        val resize_fade_anim = AnimationUtils.loadAnimation(context, R.anim.resize)
        binding.mondayButton.startAnimation(resize_fade_anim)
        binding.tuesdayButton.startAnimation(resize_fade_anim)
        binding.wednesdayButton.startAnimation(resize_fade_anim)
        binding.thursdayButton.startAnimation(resize_fade_anim)
        binding.fridayButton.startAnimation(resize_fade_anim)
        binding.saturdayButton.startAnimation(resize_fade_anim)
    }

}