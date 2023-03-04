package com.example.hairmasterplaner.ui.priceRegisterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentPriceRegisterBinding

class PriceRegisterListFragment : Fragment() {

    private var _binding:FragmentPriceRegisterBinding? = null

    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPriceRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}