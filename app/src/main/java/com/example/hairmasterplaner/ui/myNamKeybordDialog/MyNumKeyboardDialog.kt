package com.example.hairmasterplaner.ui.myNamKeybordDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentMyNumKeyBoardDialogBinding

class MyNumKeyboardDialog : DialogFragment(){

    private var _binding:FragmentMyNumKeyBoardDialogBinding? = null
    private val binding:FragmentMyNumKeyBoardDialogBinding
    get() = _binding!!

    private lateinit var viewModel: MyNumKeybordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyNumKeyBoardDialogBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[MyNumKeybordViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setupOnClickListeners(){
        with(binding){
            btn0.setOnClickListener{
                onClickListener(it)
            }
        }
    }

    private fun onClickListener(view: View){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}