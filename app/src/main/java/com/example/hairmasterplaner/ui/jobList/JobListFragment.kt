package com.example.hairmasterplaner.ui.jobList

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.*
import com.example.hairmasterplaner.databinding.FragmentJobListBinding

class JobListFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentJobListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: JobListViewModel

    private var dateStart: Long = 0

    private var dateEnd: Long = 0

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
                dateStart.getYear(),
                dateStart.getMonth()-1,
                dateStart.getDayOfMonth()
            )
                .show()
        }
        binding.tvDateEnd.setOnClickListener {
            viewModel.setCurrentTextView(TV_DATE_END)
            DatePickerDialog(
                requireContext(),
                this,
                dateEnd.getYear(),
                dateEnd.getMonth()-1,
                dateEnd.getDayOfMonth()
            )
                .show()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.changeDate(year, month, dayOfMonth)
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
        observeDateRange()
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

    private fun observeDateRange() {
        viewModel.dateRange.observe(viewLifecycleOwner){ dateRange ->
            binding.tvDateStart.text = dateRange.dateStart.toDate()
            dateStart = dateRange.dateStart
            binding.tvDateEnd.text = dateRange.dateEnd.toDate()
            dateEnd = dateRange.dateEnd
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