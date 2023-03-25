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
import com.example.hairmasterplaner.databinding.FragmentJobElementListBinding
import io.ghyeok.stickyswitch.widget.StickySwitch

class JobElementFragment : Fragment(), StickySwitch.OnSelectedChangeListener {

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
        setupRVAdapter()
        setupRecyclerView()
        observeViewModel()
        setupSwitchChangeListener()
    }

    override fun onSelectedChange(direction: StickySwitch.Direction, text: String) {
        when (direction) {
            StickySwitch.Direction.RIGHT -> {
                viewModel.listMaterial.observe(viewLifecycleOwner) {
                    rvAdapter.submitList(it)
                }
            }
            StickySwitch.Direction.LEFT -> {
                viewModel.listService.observe(viewLifecycleOwner) {
                    rvAdapter.submitList(it)
                }
            }
        }
    }

    private fun observeViewModel(){
        viewModel.listService.observe(viewLifecycleOwner){
            rvAdapter.submitList(it)
        }
    }

    private fun setupSwitchChangeListener(){
        binding.switchJobElementType.onSelectedChangeListener = this
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

    private fun mustDo(){
        TODO("Нужно пофиксить баг: После добавления новой услуги или материала, " +
                "при возвращении на фрагмент со списком услуг отображается список материалов, " +
                "а не услуг и происходит рассинхрон с состоянием свитча")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
