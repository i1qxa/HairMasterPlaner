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
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.ui.jobBodyList.CUSTOMER_RESULT_REQUEST_KEY

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
        observeResultChooseCustomer()
    }


    private fun initTextViews() {
        binding.tvDateStart.setOnClickListener {
            viewModel.setCurrentTextView(TV_DATE_START)
            DatePickerDialog(
                requireContext(),
                this,
                dateStart.getYear(),
                dateStart.getMonth() - 1,
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
                dateEnd.getMonth() - 1,
                dateEnd.getDayOfMonth()
            )
                .show()
        }
        binding.tvChooseCustomer.setOnClickListener {
            findNavController().navigate(JobListFragmentDirections.actionNavJobListToNavCustomerList(true))
        }
        binding.imgClearCustomer.setOnClickListener {
            viewModel.clearCustomerFilter()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.changeDate(year, month, dayOfMonth)
    }

    private fun observeResultChooseCustomer(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<CustomerItem>(
            CUSTOMER_RESULT_REQUEST_KEY)?.observe(viewLifecycleOwner){ customer ->
            viewModel.setCustomerFilter(customer)
        }
    }

    private fun setupRVAdapter() {
        rvAdapter = JobListRVAdapter()
        rvAdapter.onItemClickListener = { jobId ->
            viewModel.getNavigationData(jobId)
        }
    }

    private fun observeSelectedJobItemWithCustomer() {
        viewModel.selectedJobItem.observe(viewLifecycleOwner) { jobItemWithCustomer ->
            if (jobItemWithCustomer != null) {
                findNavController().navigate(
                    JobListFragmentDirections.actionNavJobListToNavJobBody(
                        jobItemWithCustomer
                    )
                )
                viewModel.clearNavigationData()
            }
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
        observeFilter()
        observeJobList()
        observeNewJob()
        observeSelectedJobItemWithCustomer()
    }

    private fun observeNewJob() {
        viewModel.newJob.observe(viewLifecycleOwner) { jobItemWithCustomer ->
            val newJob = jobItemWithCustomer
            if (newJob != null) {
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

    private fun observeFilter() {
        viewModel.jobFilter.observe(viewLifecycleOwner){ filter ->
            binding.tvDateStart.text = filter.dateRange.dateStart.toDate()
            dateStart = filter.dateRange.dateStart
            binding.tvDateEnd.text = filter.dateRange.dateEnd.toDate()
            dateEnd = filter.dateRange.dateEnd
            binding.tvChooseCustomer.text = filter.customer?.name
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