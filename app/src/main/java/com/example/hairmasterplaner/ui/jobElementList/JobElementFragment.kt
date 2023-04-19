package com.example.hairmasterplaner.ui.jobElementList

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.databinding.FragmentJobElementListBinding
import com.example.hairmasterplaner.ui.jobBodyList.JOB_ELEMENT_RESULT_REQUEST_KEY

class JobElementFragment : Fragment() {

    private var _binding: FragmentJobElementListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: JobElementRVAdapter
    private lateinit var viewModel: JobElementViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[JobElementViewModel::class.java]
        _binding = FragmentJobElementListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVAdapter()
        setupRecyclerView()
        observeViewModel()
        setupTextViewOnClickListeners()
        setupFabClickListener()
    }

    private fun observeViewModel() {
        observeJobElementList()
        observeActiveTextView()
    }

    private fun observeJobElementList(){
        viewModel.jobElementList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }


    private fun setupFabClickListener(){
        binding.fabAddJobElementItem.setOnClickListener {
            findNavController().navigate(JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(null))
        }
    }

    private fun setupRVAdapter() {
        rvAdapter = JobElementRVAdapter()
        rvAdapter.onItemClickListener = { jobElementItem ->
            if (arguments?.containsKey("choose_job_element") == true) {
                with(findNavController()) {
                    previousBackStackEntry?.savedStateHandle?.set(
                        JOB_ELEMENT_RESULT_REQUEST_KEY,
                        jobElementItem
                    )
                    popBackStack()
                }
            } else {
                findNavController().navigate(
                    JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(
                        jobElementItem
                    )
                )
            }
        }
        rvAdapter.onItemLongClickListener = {
            viewModel.deleteJobElementItem(it.id)
            TODO("Сделать проверку возможности удаления и показ диалога подтверждения удаления")
        }
    }


    private fun setupRecyclerView() {
        with(binding.rvJobElement) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupTextViewOnClickListeners() {
        binding.tvShowService.setOnClickListener {
            viewModel.showService()
        }
        binding.tvShowMaterial.setOnClickListener {
            viewModel.showMaterial()
        }
    }

    private fun observeActiveTextView() {
        viewModel.showService.observe(viewLifecycleOwner) { showService ->
            changeTextViewState(showService)
        }
    }

    private fun changeTextViewState(showService: Boolean) {
        when (showService) {
            true -> {
                binding.tvShowService.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                binding.tvShowMaterial.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
            }
            false -> {
                binding.tvShowService.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                binding.tvShowMaterial.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
