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
                onClickListener(it)
            }
            btn1.setOnClickListener {
                onClickListener(it)
            }
            btn2.setOnClickListener{
                onClickListener(it)
            }
            btn3.setOnClickListener {
                onClickListener(it)
            }
            btn4.setOnClickListener{
                onClickListener(it)
            }
            btn5.setOnClickListener {
                onClickListener(it)
            }
            btn6.setOnClickListener{
                onClickListener(it)
            }
            btn7.setOnClickListener {
                onClickListener(it)
            }
            btn8.setOnClickListener{
                onClickListener(it)
            }
            btn9.setOnClickListener {
                onClickListener(it)
            }
            btnDivision.setOnClickListener{
                onClickListener(it)
            }
            btnEqual.setOnClickListener {
                onClickListener(it)
            }
            btnMinus.setOnClickListener{
                onClickListener(it)
            }
            btnPlus.setOnClickListener {
                onClickListener(it)
            }
            btnMulti.setOnClickListener {
                onClickListener(it)
            }
            btnClear.setOnClickListener {
                onClickListener(it)
            }
        }
    }

    private fun setupBtnClearLongClickListener(){
        binding.btnClear.setOnLongClickListener {
            viewModel.clearAll()
            true
        }
    }

    private fun onClickListener(view:View){
        when(view){
            binding.btn0 -> viewModel.addNewValue(0)
            binding.btn1 -> viewModel.addNewValue(1)
            binding.btn2 -> viewModel.addNewValue(2)
            binding.btn3 -> viewModel.addNewValue(3)
            binding.btn4 -> viewModel.addNewValue(4)
            binding.btn5 -> viewModel.addNewValue(5)
            binding.btn6 -> viewModel.addNewValue(6)
            binding.btn7 -> viewModel.addNewValue(7)
            binding.btn8 -> viewModel.addNewValue(8)
            binding.btn9 -> viewModel.addNewValue(9)
            binding.btnPlus -> viewModel.addNewValue(PLUS_SIGN)
            binding.btnMinus -> viewModel.addNewValue(MINUS_SIGN)
            binding.btnMulti -> viewModel.addNewValue(MULTI_SIGN)
            binding.btnDivision -> viewModel.addNewValue(DIVISION_SIGN)
            binding.btnEqual -> viewModel.addNewValue(EQUAL_SIGN)
            binding.btnClear -> viewModel.deleteLastChar()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}