package com.example.hairmasterplaner.ui.priceRegisterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentPriceRegisterBinding
import io.ghyeok.stickyswitch.widget.StickySwitch

class PriceRegisterListFragment : Fragment(),StickySwitch.OnSelectedChangeListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.switchJobElementType.onSelectedChangeListener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSelectedChange(direction: StickySwitch.Direction, text: String) {
        Toast.makeText(requireContext(),text,Toast.LENGTH_SHORT).show()
    }
}