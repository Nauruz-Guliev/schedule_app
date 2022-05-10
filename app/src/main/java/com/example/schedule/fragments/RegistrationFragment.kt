package com.example.schedule.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.schedule.R
import com.example.schedule.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater,container,false)

        //animations on elements load
        val resize_fade_anim = AnimationUtils.loadAnimation(context, R.anim.resize_fade_anim)
        val resize_cool_anim = AnimationUtils.loadAnimation(context, R.anim.resize_cool_anim)
        binding.registrationSaveButton.startAnimation(resize_fade_anim)
        binding.getToKnowTxtView.startAnimation(resize_fade_anim)
        binding.firstNameInputLayout.startAnimation(resize_cool_anim)
        binding.lastNameInputLayout.startAnimation(resize_cool_anim)
        //
        binding.registrationSaveButton.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.getToKnowTxtView to "welcome_message")
            Navigation.findNavController(binding.root).navigate(R.id.action_registrationFragment_to_groupSelectionFragment,null,null, extras)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}