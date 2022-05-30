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
import com.example.schedule.databinding.FragmentGroupSelectionBinding
import com.example.schedule.databinding.FragmentRegistrationBinding
import com.example.schedule.scheduleParser.Parser
import org.json.JSONArray


class GroupSelectionFragment : Fragment() {
    private var _binding: FragmentGroupSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGroupSelectionBinding.inflate(inflater,container,false)

        //setting up group choices
        val groupNames = resources.getStringArray(R.array.Название_Группы)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item,groupNames)
        binding.GroupsSelectionTextView.setAdapter(arrayAdapter)
        var parser = arguments?.getSerializable("parser") as Parser

        val bundle = Bundle()
        bundle.putString("group", "0")
        bundle.putSerializable("parser",parser)
        //onlicklistener
        binding.groupSaveButton.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.groupSaveButton to "week_button_transition")
            Navigation.findNavController(binding.root).navigate(R.id.action_groupSelectionFragment_to_dayOfWeekFragment, bundle,null, extras)
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
}