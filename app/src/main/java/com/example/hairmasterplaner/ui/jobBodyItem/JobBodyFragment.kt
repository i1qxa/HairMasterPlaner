package com.example.hairmasterplaner.ui.jobBodyItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobBodyBinding
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import com.example.hairmasterplaner.ui.customerList.CustomerListFragmentDirections
import com.example.hairmasterplaner.ui.printToLog
import com.example.hairmasterplaner.ui.toDateTime

const val CUSTOMER_RESULT_REQUEST_KEY = "customer"
const val AMOUNT_RESULT_REQUEST_KEY = "amount"
const val PRICE_RESULT_REQUEST_KEY = "price"
class JobBodyFragment : Fragment(){

    private val args by navArgs<JobBodyFragmentArgs>()
    private lateinit var viewModel: JobBodyViewModel
    private var _binding:FragmentJobBodyBinding? = null
    private val binding
    get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[JobBodyViewModel::class.java]
        _binding = FragmentJobBodyBinding.inflate(inflater,container,false)
        parseArgs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCustomerClickListener()
        observeCustomer()
        observeResultChooseCustomer()
    }

    private fun setupCustomerClickListener(){
        binding.tvChooseCustomer.setOnClickListener {
            findNavController().navigate(JobBodyFragmentDirections.actionNavJobBodyToNavCustomerList(true))
        }
    }

    private fun observeCustomer(){
        viewModel.jobItemWithCustomerLD.observe(viewLifecycleOwner){
            binding.tvChooseCustomer.text = it.customerItem.name
            binding.tvDate.text = it.jobItem.dateInMils.toDateTime()
        }
    }

    private fun observeResultChooseCustomer(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<CustomerItem>(
            CUSTOMER_RESULT_REQUEST_KEY)?.observe(viewLifecycleOwner) { result ->
            viewModel.addOrEditJobItem(result)
        }
    }

    private fun parseArgs(){
        if (args.jobItemWithCustomerItem != null){
            viewModel.initJobItemWithCustomer(args.jobItemWithCustomerItem!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}