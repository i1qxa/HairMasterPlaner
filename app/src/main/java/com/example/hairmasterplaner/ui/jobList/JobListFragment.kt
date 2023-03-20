package com.example.hairmasterplaner.ui.jobList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hairmasterplaner.databinding.FragmentJobListBinding

class JobListFragment : Fragment() {

    private var _binding: FragmentJobListBinding? = null

    private val binding get() = _binding!!

    private lateinit var calendar:CalendarView

    private lateinit var viewModel:JobListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[JobListViewModel::class.java]

        _binding = FragmentJobListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun setupDateChangeListener(){
        calendar = binding.calendarChooseDate
        calendar.setOnDateChangeListener { view, year, month, day ->

        }
    }


    private fun observeViewModel(){
        viewModel.dateStart.observe(viewLifecycleOwner){
            binding.tvDateStart.text = it
        }
        viewModel.dateEnd.observe(viewLifecycleOwner){
            binding.tvDateEnd.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}