package com.example.schedule.fragments

import android.os.Bundle
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


class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var names: List<String>
    private var layoutManager:RecyclerView.LayoutManager? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        // просто имена - пустышки
        names = listOf("Name1", "Name2")
        // всегда используйте binding и в других фрагментах
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = CustomRecyclerAdapter(names);

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}