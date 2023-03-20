package com.example.hairmasterplaner.ui.jobList

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobListBinding
import java.util.Calendar

class JobListFragment : Fragment(), OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentJobListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: JobListViewModel

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
        setupSpinner()
        initTextViews()
    }

    private fun initTextViews(){
        binding.tvDateStart.setOnClickListener {
            viewModel.setCurrentTextView(DATE_START)
            DatePickerDialog(requireContext(),
                this,
                viewModel.calendarStart.get(Calendar.YEAR),
                viewModel.calendarStart.get(Calendar.MONTH),
                viewModel.calendarStart.get(Calendar.DAY_OF_MONTH))
                .show()
        }
        binding.tvDateEnd.setOnClickListener {
            viewModel.setCurrentTextView(DATE_END)
            DatePickerDialog(requireContext(),
            this,
            viewModel.calendarEnd.get(Calendar.YEAR),
            viewModel.calendarEnd.get(Calendar.MONTH),
            viewModel.calendarEnd.get(Calendar.DAY_OF_MONTH))
                .show()
        }
    }


    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfWeek: Int) {
        viewModel.changeDate(year,month,dayOfWeek)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        viewModel.setupCurrentPeriod(position)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun observeViewModel() {
        viewModel.dateStart.observe(viewLifecycleOwner) {
            binding.tvDateStart.text = it
        }
        viewModel.dateEnd.observe(viewLifecycleOwner) {
            binding.tvDateEnd.text = it
        }
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.date_period_list,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerChoosePeriod.adapter = spinnerAdapter
        binding.spinnerChoosePeriod.onItemSelectedListener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}