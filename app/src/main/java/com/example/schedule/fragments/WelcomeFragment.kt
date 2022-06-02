package com.example.schedule.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.schedule.R
import com.example.schedule.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        var bundle = Bundle()

        binding.button.setOnClickListener {
            if (binding.checkBox.isChecked) {
                Navigation.findNavController(binding.root).navigate(R.id.action_welcomeFragment_to_registrationFragment)
            }
            if (binding.checkBox3.isChecked) {
                val sp = context?.getSharedPreferences("PreferencesData", Context.MODE_PRIVATE)
                bundle.putString("name", sp?.getString("name", "Иван"))
                bundle.putString("group", sp?.getString("group", "11-101"))

                Navigation.findNavController(binding.root).navigate(R.id.action_welcomeFragment_to_dayOfWeekFragment, bundle)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
