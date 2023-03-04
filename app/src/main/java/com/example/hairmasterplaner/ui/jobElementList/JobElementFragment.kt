package com.example.hairmasterplaner.ui.jobElementList

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobElementListBinding
import com.example.hairmasterplaner.ui.jobElementList.jobElementItem.JobElementItemFragment
import com.example.hairmasterplaner.ui.jobElementList.jobElementItem.JobElementItemFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
            ViewModelProvider(this).get(JobElementViewModel::class.java)

        _binding = FragmentJobElementListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupFabClickListener()
    }

    private fun setupFabClickListener(){
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.visibility = View.VISIBLE
        fab.setOnClickListener {
            findNavController().navigate(JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(-1))
        }
    }

    private fun setupRecyclerView(){
        rvAdapter = JobElementRVAdapter()
        with(binding.rvJobElement){
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeViewModel(){
        viewModel.listJobElement.observe(viewLifecycleOwner){
            rvAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}