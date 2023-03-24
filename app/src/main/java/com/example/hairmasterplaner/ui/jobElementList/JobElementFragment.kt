package com.example.hairmasterplaner.ui.jobElementList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobElementListBinding

class JobElementFragment : Fragment() {

    private var _binding: FragmentJobElementListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: JobElementRVAdapter
    private lateinit var viewModel: JobElementViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[JobElementViewModel::class.java]
        _binding = FragmentJobElementListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwitchChangeListener()
        setupRVAdapter()
        setupRecyclerView()
        observeViewModel()
    }


    private fun setupSwitchChangeListener() {
        binding.switchJobElementType.setOnCheckedChangeListener { compoundButton, b ->
            //observeViewModel()
            if(b){
                viewModel.listService.observe(viewLifecycleOwner){
                    rvAdapter.submitList(it)
                }
                compoundButton.text = getString(R.string.switchNameServices)
            }
            else{
                viewModel.listMaterial.observe(viewLifecycleOwner){
                    rvAdapter.submitList(it)
                }
                compoundButton.text = getString(R.string.switchNameMaterials)
            }
        }

    }

    private fun setupRVAdapter() {
        rvAdapter = JobElementRVAdapter()
        rvAdapter.onItemClickListener = {
            findNavController().navigate(
                JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(it)
            )
        }
        rvAdapter.onItemLongClickListener = {
            viewModel.deleteJobElementItem(it.id)
            TODO("Сделать проверку возможности удаления и показ диалога подтверждения удаления")
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvJobElement) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeViewModel(){
        if(binding.switchJobElementType.isChecked){
            viewModel.listService.observe(viewLifecycleOwner){
                rvAdapter.submitList(it)
            }
            binding.switchJobElementType.text = getString(R.string.switchNameServices)
        }
        else{
            viewModel.listMaterial.observe(viewLifecycleOwner){
                rvAdapter.submitList(it)
            }
            binding.switchJobElementType.text = getString(R.string.switchNameMaterials)
        }
    }


    private fun manageSwitchState(isService: Boolean) {
        val switchText = if (isService) getString(R.string.switchNameServices) else getString(R.string.switchNameMaterials)
        with(binding.switchJobElementType) {
            text = switchText
            isChecked = isService
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}