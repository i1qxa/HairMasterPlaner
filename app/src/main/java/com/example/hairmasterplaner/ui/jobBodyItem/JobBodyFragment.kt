package com.example.hairmasterplaner.ui.jobBodyItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.databinding.FragmentJobBodyBinding
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import com.example.hairmasterplaner.ui.toDateTime

const val CUSTOMER_RESULT_REQUEST_KEY = "customer"
const val DIGIT_EDIT_RESULT_REQUEST_KEY = "amount"
const val JOB_ELEMENT_RESULT_REQUEST_KEY = "job_element"
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
        observeViewModel()
        observeRequestFromOtherFragments()
        setupClickListeners()
    }

    private fun observeViewModel(){
        observeAmountOfNewItem()
        observePriceOfNewItem()
        observeCustomer()
        observeJobElement()
    }

    private fun observeRequestFromOtherFragments(){
        observeResultChooseCustomer()
        observeResultChooseJobElement()
        observeResultEditDigit()
    }

    private fun setupClickListeners(){
        setupChooseCustomerClickListener()
        setupAmountClickListener()
        setupPriceClickListener()
        setupChoseJobElementClickListener()

    }
    private fun setupChooseCustomerClickListener(){
        binding.tvChooseCustomer.setOnClickListener {
            findNavController().navigate(JobBodyFragmentDirections.actionNavJobBodyToNavCustomerList(true))
        }
    }

    private fun setupChoseJobElementClickListener(){
        binding.tvChooseJobElement.setOnClickListener {
            findNavController().navigate(JobBodyFragmentDirections.actionNavJobBodyToNavJobElementList(true))
        }
    }
    private fun setupAmountClickListener(){
        binding.tvEnterAmount.setOnClickListener {
            viewModel.setupCurrentEditingTV(NEW_ITEM_AMOUNT)
            val numToEdit = if(binding.tvEnterAmount.text.isNotEmpty())
                binding.tvEnterAmount.text.toString().toInt()
            else 0
            findNavController().navigate(JobBodyFragmentDirections.actionNavJobBodyToMyNumKeyboardDialog(numToEdit))
        }
    }

    private fun setupPriceClickListener(){
        binding.tvEnterPrice.setOnClickListener {
            viewModel.setupCurrentEditingTV(NEW_ITEM_PRICE)
            val numToEdit = if(binding.tvEnterPrice.text.isNotEmpty())
                binding.tvEnterPrice.text.toString().toInt()
            else 0
            findNavController().navigate(JobBodyFragmentDirections.actionNavJobBodyToMyNumKeyboardDialog(numToEdit))
        }
    }

    private fun setupBtnAddJobBodyElementClickListener(){
        binding.btnAddJobBodyItem.setOnClickListener {
            viewModel.addJobBodyItem()
        }
    }

    private fun observeCustomer(){
        viewModel.jobItemWithCustomerLD.observe(viewLifecycleOwner){
            binding.tvChooseCustomer.text = it.customerItem.name
            binding.tvDate.text = it.jobItem.dateInMils.toDateTime()
        }
    }

    private fun observeJobElement(){
        viewModel.newJobElementItem.observe(viewLifecycleOwner){
            binding.tvChooseJobElement.text = it?.name
        }
    }

    private fun observeAmountOfNewItem(){
        viewModel.amountOfNewItem.observe(viewLifecycleOwner){ amount ->
            with(binding.tvEnterAmount){
                text = amount.toString()
            }
        }
    }

    private fun observePriceOfNewItem(){
        viewModel.priceOfNewItem.observe(viewLifecycleOwner){ price ->
            with(binding.tvEnterPrice){
                text = price.toString()
            }
        }
    }

    private fun observeResultEditDigit(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(
            DIGIT_EDIT_RESULT_REQUEST_KEY)?.observe(viewLifecycleOwner){ num ->
            viewModel.updateNum(num)
        }
    }

    private fun observeResultChooseJobElement(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<JobElementItem>(
            JOB_ELEMENT_RESULT_REQUEST_KEY)?.observe(viewLifecycleOwner){ elementItem ->
            viewModel.setupNewJobElement(elementItem)
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