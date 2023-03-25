package com.example.hairmasterplaner.ui.jobElementList.jobElementItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobElementItemEditingBinding
import com.example.hairmasterplaner.ui.gone
import com.example.hairmasterplaner.ui.visible


class JobElementItemFragment : DialogFragment() {

    private val args by navArgs<JobElementItemFragmentArgs>()
    private var _binding: FragmentJobElementItemEditingBinding? = null
    private val binding: FragmentJobElementItemEditingBinding
        get() = _binding!!
    private lateinit var viewModel: JobElementItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[JobElementItemViewModel::class.java]
        _binding = FragmentJobElementItemEditingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupSwitchChangeListener()
        setupBtnClickListeners()
    }

    private fun initViews() {
        if (args.jobElementItem != null) {
            val item = args.jobElementItem
            with(binding) {
                etJobElementName.setText(item?.name)
                etPrice.setText(item?.price.toString())
                if (item?.isService!!) {
                    switchIsService.isChecked = true
                    switchIsService.setText(R.string.switchNameServices)
                    etUnitOM.gone()
                } else {
                    switchIsService.isChecked = false
                    switchIsService.setText(R.string.switchNameMaterials)
                    etUnitOM.visible()
                    etUnitOM.setText(item.unitOM)
                }
            }
        }
    }

    private fun setupBtnClickListeners() {
        binding.btnSave.setOnClickListener {
            if (args.jobElementItem == null) {
                viewModel.addJobElement(
                    binding.etJobElementName.text.toString(),
                    binding.switchIsService.isChecked,
                    binding.etUnitOM.text.toString(),
                    binding.etPrice.text.toString().toInt()
                )
            } else {
                viewModel.editJobElement(
                    args.jobElementItem!!.id,
                    binding.etJobElementName.text.toString(),
                    binding.switchIsService.isChecked,
                    binding.etUnitOM.text.toString(),
                    binding.etPrice.text.toString().toInt()
                )
            }
            findNavController().popBackStack()
        }
        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupSwitchChangeListener() {
        binding.switchIsService.setOnCheckedChangeListener { compoundButton, b ->
            val etUnitOM = binding.etUnitOM
            if (compoundButton.isChecked) {
                compoundButton.text = "Услуга"
                etUnitOM.gone()
            } else {
                compoundButton.text = "Материал"
                etUnitOM.visible()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}