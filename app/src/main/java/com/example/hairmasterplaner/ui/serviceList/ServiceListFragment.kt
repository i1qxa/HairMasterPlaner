package com.example.hairmasterplaner.ui.serviceList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.databinding.FragmentServiceListBinding
import com.example.hairmasterplaner.ui.jobBodyList.JOB_ELEMENT_RESULT_REQUEST_KEY

class ServiceListFragment : Fragment() {

    private var _binding: FragmentServiceListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: ServiceListRVAdapter
    private lateinit var viewModel: ServiceListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[ServiceListViewModel::class.java]
        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVAdapter()
        setupRecyclerView()
        observeViewModel()
        setupFabClickListener()
    }

    private fun observeViewModel() {
        observeJobElementList()
    }

    private fun observeJobElementList() {
        viewModel.serviceList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }


    private fun setupFabClickListener() {
        binding.fabAddServiceItem.setOnClickListener {
            findNavController().navigate(
                JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(
                    null
                )
            )
        }
    }

    private fun setupRVAdapter() {
        rvAdapter = ServiceListRVAdapter()
        rvAdapter.onItemClickListener = { serviceItem ->
            if (arguments?.containsKey("choose_job_element") == true) {
                with(findNavController()) {
                    previousBackStackEntry?.savedStateHandle?.set(
                        JOB_ELEMENT_RESULT_REQUEST_KEY,
                        serviceItem
                    )
                    popBackStack()
                }
            } else {
                findNavController().navigate(
                    JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(
                        serviceItem
                    )
                )
            }
        }
        rvAdapter.onItemLongClickListener = {
            viewModel.deleteServiceItem(it.id)
            TODO("Сделать проверку возможности удаления и показ диалога подтверждения удаления")
        }
    }


    private fun setupRecyclerView() {
        with(binding.rvServiceList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
