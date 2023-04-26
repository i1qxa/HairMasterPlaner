package com.example.hairmasterplaner.ui.materialList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.databinding.FragmentMaterialListBinding
import com.example.hairmasterplaner.domain.materials.MaterialItem
import com.example.hairmasterplaner.ui.jobBodyList.DIGIT_EDIT_RESULT_REQUEST_KEY

const val RESPONSE_NEW_MATERIAL_ITEM = "new_material_item"

class MaterialListFragment : DialogFragment() {

    private var _binding:FragmentMaterialListBinding? = null
    private lateinit var viewModel: MaterialListViewModel
    private val binding
    get() = _binding!!
    private lateinit var rvAdapter: MaterialListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MaterialListViewModel::class.java]
        _binding = FragmentMaterialListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvAdapter()
        initRecyclerView()
        observeResultEditMaterialItem()
    }

    private fun initRvAdapter(){
        rvAdapter = MaterialListRVAdapter()
        rvAdapter.onItemClickListener = { materialItem ->
            findNavController().navigate(MaterialListFragmentDirections.
            actionNavMaterialListToMaterialItemFragment(materialItem))
        }
        viewModel.materialItemList.observe(viewLifecycleOwner){
            rvAdapter.submitList(it)
        }
    }

    private fun initRecyclerView(){
        with(binding.rvListMaterial){
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeResultEditMaterialItem(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<MaterialItem>(
            RESPONSE_NEW_MATERIAL_ITEM
        )?.observe(viewLifecycleOwner) { materialItem ->
            viewModel.addOrEditMaterialItem(materialItem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}