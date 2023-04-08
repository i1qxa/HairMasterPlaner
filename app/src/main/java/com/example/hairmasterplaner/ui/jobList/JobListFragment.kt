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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobListBinding

class JobListFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentJobListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: JobListViewModel

    private lateinit var dateStart: Date

    private lateinit var dateEnd: Date

    private lateinit var rvAdapter: JobListRVAdapter

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
        viewModel.clearNewJob()
        initTextViews()
        setupFabClickListener()
        setupRVAdapter()
        setupRecyclerView()
        observeViewModel()
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

    private fun setupRVAdapter() {
        rvAdapter = JobListRVAdapter()
        rvAdapter.onItemClickListener = {
            findNavController().navigate(JobListFragmentDirections.actionNavJobListToNavJobBody(it))
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvJobList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeViewModel() {
        observeDateStart()
        observeDateEnd()
        observeJobList()
        observeNewJob()
    }

    private fun observeNewJob() {
        viewModel.newJob.observe(viewLifecycleOwner) { jobItemWithCustomer ->
            val newJob = jobItemWithCustomer
            if (newJob!=null){
                findNavController().navigate(
                    JobListFragmentDirections.actionNavJobListToNavJobBody(
                        newJob
                    )
                )
            }
        }
    }

    private fun setupFabClickListener() {
        binding.fabAddNewJob.setOnClickListener {
            viewModel.addNewJobItem()
        }
    }

    private fun observeDateStart() {
        viewModel.dateStart.observe(viewLifecycleOwner) {
            binding.tvDateStart.text = it.getFormattedDate()
            dateStart = it
        }
    }

    private fun observeDateEnd() {
        viewModel.dateEnd.observe(viewLifecycleOwner) {
            binding.tvDateEnd.text = it.getFormattedDate()
            dateEnd = it
        }
    }

    private fun observeJobList() {
        viewModel.listOfJob.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}