package com.example.hairmasterplaner.ui.jobList

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobListBinding

class JobListFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentJobListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: JobListViewModel

    private lateinit var dateStart: Date

    private lateinit var dateEnd: Date

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
        initTextViews()
    }


    private fun initTextViews() {
        binding.tvDateStart.setOnClickListener {
            viewModel.setCurrentTextView(TV_DATE_START)
            DatePickerDialog(
                requireContext(),
                this,
                dateStart.year,
                dateStart.month,
                dateStart.dayOfMonth
            )
                .show()
        }
        binding.tvDateEnd.setOnClickListener {
            viewModel.setCurrentTextView(TV_DATE_END)
            DatePickerDialog(
                requireContext(),
                this,
                dateEnd.year,
                dateEnd.month,
                dateEnd.dayOfMonth
            )
                .show()
        }
    }


    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.changeDate(Date(year, month, dayOfMonth))
    }

    private fun observeViewModel() {
        viewModel.dateStart.observe(viewLifecycleOwner) {
            binding.tvDateStart.text = it.getFormattedDate()
            dateStart = it
        }
        viewModel.dateEnd.observe(viewLifecycleOwner) {
            binding.tvDateEnd.text = it.getFormattedDate()
            dateEnd = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}