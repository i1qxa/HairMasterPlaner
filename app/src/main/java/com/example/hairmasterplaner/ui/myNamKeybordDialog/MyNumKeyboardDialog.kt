package com.example.hairmasterplaner.ui.myNamKeybordDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.hairmasterplaner.databinding.FragmentMyNumKeyBoardDialogBinding

class MyNumKeyboardDialog : DialogFragment(){

    private var _binding:FragmentMyNumKeyBoardDialogBinding? = null
    private val binding:FragmentMyNumKeyBoardDialogBinding
    get() = _binding!!

    private lateinit var viewModel: MyNumKeyboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        setupBtnOnClickListeners()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.resultLD.observe(viewLifecycleOwner){
            binding.tvCalcScreen.text = it
        }
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
        }
    }

    private fun onClickListener(view:View){
        when(view){
            binding.btn0 -> viewModel.addNewValue(binding.btn0.text.toString())
            binding.btn1 -> viewModel.addNewValue(binding.btn1.text.toString())
            binding.btn2 -> viewModel.addNewValue(binding.btn2.text.toString())
            binding.btn3 -> viewModel.addNewValue(binding.btn3.text.toString())
            binding.btn4 -> viewModel.addNewValue(binding.btn4.text.toString())
            binding.btn5 -> viewModel.addNewValue(binding.btn5.text.toString())
            binding.btn6 -> viewModel.addNewValue(binding.btn6.text.toString())
            binding.btn7 -> viewModel.addNewValue(binding.btn7.text.toString())
            binding.btn8 -> viewModel.addNewValue(binding.btn8.text.toString())
            binding.btn9 -> viewModel.addNewValue(binding.btn9.text.toString())
            binding.btnPlus -> viewModel.addNewValue(binding.btnPlus.text.toString())
            binding.btnMinus -> viewModel.addNewValue(binding.btnMinus.text.toString())
            binding.btnMulti -> viewModel.addNewValue(binding.btnMulti.text.toString())
            binding.btnDivision -> viewModel.addNewValue(binding.btnDivision.text.toString())
            binding.btnEqual -> viewModel.addNewValue(binding.btnEqual.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}