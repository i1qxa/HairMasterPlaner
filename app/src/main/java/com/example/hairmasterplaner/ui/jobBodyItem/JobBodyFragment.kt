package com.example.hairmasterplaner.ui.jobBodyItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentJobBodyBinding

class JobBodyFragment : Fragment() {

    private val args by navArgs<JobBodyFragmentArgs>()
    private lateinit var viewModel: JobBodyViewModel
    private var _binding:FragmentJobBodyBinding? = null
    private val binding
    get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[JobBodyViewModel::class.java]
        _binding = FragmentJobBodyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun parseArgs(){
        if (args.jobItem == null){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}