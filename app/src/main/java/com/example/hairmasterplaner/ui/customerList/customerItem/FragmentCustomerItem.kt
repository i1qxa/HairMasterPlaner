package com.example.hairmasterplaner.ui.customerList.customerItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentCustomerItemEditingBinding
import com.example.hairmasterplaner.ui.jobElementList.jobElementItem.JobElementItemFragmentArgs

class FragmentCustomerItem : DialogFragment() {

    private val args by navArgs<FragmentCustomerItemArgs>()
    private var _binding: FragmentCustomerItemEditingBinding? = null
    private val binding: FragmentCustomerItemEditingBinding
        get() = _binding!!
    private lateinit var viewModel: CustomerItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerItemEditingBinding.inflate(
            layoutInflater,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[CustomerItemViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setOnClickListeners()
        observeViewModel()
    }

    private fun initViews() {
        if (args.customerItem != null) {
            val customerItem = args.customerItem
            binding.etCustomerName.setText(customerItem?.name)
            binding.etPhoneNumber.setText(customerItem?.telNumber)
        }
    }

    private fun setOnClickListeners(){
        binding.btnSaveCustomer.setOnClickListener {
            viewModel.saveCustomer(
                args.customerItem?.id ?:0,
                binding.etCustomerName.text.toString(),
                binding.etPhoneNumber.text.toString()
            )
        }
        binding.btnCancelCustomer.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel(){
        viewModel.isFinish.observe(viewLifecycleOwner){
            showSuccessMessage()
        }
    }

    private fun showSuccessMessage(){
        Toast.makeText(context,
            getString(R.string.toast_msg_success_creating_customer),
            Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}