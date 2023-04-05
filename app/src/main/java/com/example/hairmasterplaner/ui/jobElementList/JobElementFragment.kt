package com.example.hairmasterplaner.ui.jobElementList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.databinding.FragmentJobElementListBinding
import com.example.hairmasterplaner.ui.jobBodyItem.JOB_ELEMENT_RESULT_REQUEST_KEY
import io.ghyeok.stickyswitch.widget.StickySwitch

class JobElementFragment : Fragment(), StickySwitch.OnSelectedChangeListener {

    private var _binding: FragmentJobElementListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: JobElementRVAdapter
    private lateinit var viewModel: JobElementViewModel
    private val args by navArgs<JobElementFragmentArgs>()

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
        rvAdapter.onItemClickListener = { jobElementItem ->
            if (args.chooseJobElement){
                with(findNavController()){
                    previousBackStackEntry?.savedStateHandle?.set(
                        JOB_ELEMENT_RESULT_REQUEST_KEY,
                        jobElementItem
                    )
                    popBackStack()
                }
            }
            else{
                findNavController().navigate(
                    JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(jobElementItem)
                )
            }
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
