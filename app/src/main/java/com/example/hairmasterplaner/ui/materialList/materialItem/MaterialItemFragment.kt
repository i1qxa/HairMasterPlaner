package com.example.hairmasterplaner.ui.materialList.materialItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentMaterialItemBinding
import com.example.hairmasterplaner.domain.materials.MaterialItem
import com.example.hairmasterplaner.ui.materialList.RESPONSE_NEW_MATERIAL_ITEM
import com.example.hairmasterplaner.ui.toast

class MaterialItemFragment : Fragment() {

    private lateinit var binding:FragmentMaterialItemBinding
    private lateinit var viewModel:MaterialItemViewModel
    private val args by navArgs<MaterialItemFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MaterialItemViewModel::class.java]
        binding = FragmentMaterialItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgs()
        setBtnOnClickListeners()
        observeViewModel()
    }

    private fun parseArgs(){
        if (args.materialItem!=null){
            inflateViews(args.materialItem!!)
        }
    }

    private fun inflateViews(materialItem:MaterialItem){
        with(binding){
            etMaterialName.setText(materialItem.name)
            etMaterialEnterPurchasePrice.setText(materialItem.purchasePrice.toString())
            etMaterialRtlPrice.setText(materialItem.rtlPrice.toString())
        }
        viewModel.setupMaterialItemId(materialItem.id)
    }

    private fun setBtnOnClickListeners(){
        with(binding){
            btnMaterialCancel.setOnClickListener {
                findNavController().popBackStack()
            }
            btnMaterialSave.setOnClickListener {
                viewModel.prepareResponse(
                    materialName = binding.etMaterialName.text.toString(),
                    purchasePriceStr = binding.etMaterialEnterPurchasePrice.text.toString(),
                    rtlPriceStr = binding.etMaterialRtlPrice.text.toString()
                )
            }
        }
    }

    private fun observeViewModel(){
        observeErrorName()
        observeResponse()
    }

    private fun observeErrorName(){
        viewModel.errorEnterName.observe(viewLifecycleOwner){
            if (it){
                toast(getString(R.string.error_material_name_should_not_be_empty))
            }
        }
    }

    private fun observeResponse(){
        viewModel.response.observe(viewLifecycleOwner){ materialItem ->
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                RESPONSE_NEW_MATERIAL_ITEM,materialItem)
            findNavController().popBackStack()
        }
    }


}