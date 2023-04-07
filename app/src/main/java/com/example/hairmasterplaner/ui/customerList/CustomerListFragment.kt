package com.example.hairmasterplaner.ui.customerList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.databinding.FragmentCustomerListBinding
import com.example.hairmasterplaner.ui.jobBodyList.CUSTOMER_RESULT_REQUEST_KEY

class CustomerListFragment : Fragment() {

    private var _binding: FragmentCustomerListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: CustomerRVAdapter
    private lateinit var customerViewModel: CustomerListViewModel
    private val args by navArgs<CustomerListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        customerViewModel =
            ViewModelProvider(this)[CustomerListViewModel::class.java]
        _binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVAdapter()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRVAdapter() {
        rvAdapter = CustomerRVAdapter()
        with(rvAdapter) {
            onItemClickListener = {
                if (!args.chooseCustomer) {
                    findNavController().navigate(
                        CustomerListFragmentDirections.actionNavCustomerListToFragmentCustomerItem(
                            it
                        )
                    )
                }
                else{
                    findNavController().previousBackStackEntry?.savedStateHandle?.set(
                        CUSTOMER_RESULT_REQUEST_KEY,it)
                    findNavController().popBackStack()
                }
            }
            onItemLongClickListener = {
                TODO("Реализовать удаление элемента")
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvCustomerList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeViewModel() {
        customerViewModel.customerList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}