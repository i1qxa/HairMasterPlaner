package com.example.hairmasterplaner.ui.jobElementList.jobElementItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobElementItemEditingBinding


class JobElementItemFragment : DialogFragment() {

    private var _binding:FragmentJobElementItemEditingBinding? = null
    private val binding:FragmentJobElementItemEditingBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobElementItemEditingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwitchChangeListener()
    }

    private fun setupBtnClickListeners(){
        binding.btnSave.setOnClickListener {

        }
        binding.btnCancel.setOnClickListener {

        }
    }

    private fun setupSwitchChangeListener(){
        binding.switchIsService.setOnCheckedChangeListener { compoundButton, b ->
            val etUnitOM = binding.etUnitOM
            if (compoundButton.isChecked) etUnitOM.visibility = View.GONE
            else etUnitOM.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}