package com.example.hairmasterplaner.ui.priceRegisterList

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.hairmasterplaner.databinding.FragmentPriceRegisterBinding
import com.example.hairmasterplaner.ui.customerList.REQUEST_CODE_READ_CONTACT
import com.example.hairmasterplaner.ui.printToLog

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddContacts.setOnClickListener {
            readContacts()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun readContacts(){
        if (checkPermission()){
            val coursor = requireActivity().contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null,
            )
            coursor?.let {
                while (it.moveToNext()){
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)).printToLog("CONTACT_NAME")
                }
            }
        }
    }

    private fun checkPermission():Boolean{
        val permissionStatus = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CONTACTS)
        when(permissionStatus){
            PackageManager.PERMISSION_GRANTED -> return true
            else -> {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_CODE_READ_CONTACT
                )
                return false
            }
        }
    }

}