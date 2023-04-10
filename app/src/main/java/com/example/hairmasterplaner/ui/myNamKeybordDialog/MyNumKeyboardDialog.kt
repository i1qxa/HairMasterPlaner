package com.example.hairmasterplaner.ui.myNamKeybordDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.databinding.FragmentMyNumKeyBoardDialogBinding
import com.example.hairmasterplaner.ui.jobBodyList.DIGIT_EDIT_RESULT_REQUEST_KEY
import com.example.hairmasterplaner.ui.toast

class MyNumKeyboardDialog : DialogFragment(){

    private val args by navArgs<MyNumKeyboardDialogArgs>()
    private var _binding:FragmentMyNumKeyBoardDialogBinding? = null
    private val binding:FragmentMyNumKeyBoardDialogBinding
    get() = _binding!!

    private lateinit var viewModel: MyNumKeyboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyNumKeyBoardDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MyNumKeyboardViewModel::class.java]
        parseArgs()
        setupBtnOnClickListeners()
        setupBtnClearLongClickListener()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.resultLD.observe(viewLifecycleOwner){
            binding.tvCalcScreen.text = it
        }
        viewModel.errorToastMessage.observe(viewLifecycleOwner){
            toast(it)
        }
        viewModel.shouldFinishWork.observe(viewLifecycleOwner){
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                DIGIT_EDIT_RESULT_REQUEST_KEY,it)
            findNavController().popBackStack()
        }
    }

    private fun parseArgs(){
        viewModel.setupFirstDigit(args.digitToEdit)
    }

    private fun setupBtnOnClickListeners(){
        with(binding){
            btn0.setOnClickListener{
                viewModel.addNewValue(0)
            }
            btn1.setOnClickListener {
                viewModel.addNewValue(1)
            }
            btn2.setOnClickListener{
                viewModel.addNewValue(2)
            }
            btn3.setOnClickListener {
                viewModel.addNewValue(3)
            }
            btn4.setOnClickListener{
                viewModel.addNewValue(4)
            }
            btn5.setOnClickListener {
                viewModel.addNewValue(5)
            }
            btn6.setOnClickListener{
                viewModel.addNewValue(6)
            }
            btn7.setOnClickListener {
                viewModel.addNewValue(7)
            }
            btn8.setOnClickListener{
                viewModel.addNewValue(8)
            }
            btn9.setOnClickListener {
                viewModel.addNewValue(9)
            }
            btnDivision.setOnClickListener{
                viewModel.addNewValue(DIVISION_SIGN)
            }
            btnEqual.setOnClickListener {
                viewModel.addNewValue(EQUAL_SIGN)
            }
            btnMinus.setOnClickListener{
                viewModel.addNewValue(MINUS_SIGN)
            }
            btnPlus.setOnClickListener {
                viewModel.addNewValue(PLUS_SIGN)
            }
            btnMulti.setOnClickListener {
                viewModel.addNewValue(MULTI_SIGN)
            }
            btnClear.setOnClickListener {
                viewModel.deleteLastChar()
            }
        }
    }

    private fun setupBtnClearLongClickListener(){
        binding.btnClear.setOnLongClickListener {
            viewModel.clearAll()
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}