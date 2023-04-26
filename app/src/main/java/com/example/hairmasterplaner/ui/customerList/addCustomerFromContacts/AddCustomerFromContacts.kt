package com.example.hairmasterplaner.ui.customerList.addCustomerFromContacts

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.databinding.FragmentAddCustomerFromContactsBinding
import com.example.hairmasterplaner.domain.customer.ContactsItem
import com.example.hairmasterplaner.ui.customerList.REQUEST_CODE_READ_CONTACT
import com.example.hairmasterplaner.ui.printToLog

class AddCustomerFromContacts : Fragment() {

    private var _binding: FragmentAddCustomerFromContactsBinding? = null
    private val binding: FragmentAddCustomerFromContactsBinding
        get() = _binding!!
    private lateinit var rvAdapter:AddCustomerFromContactsRVAdapter
    private var listOfContacts = mutableListOf<ContactsItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCustomerFromContactsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVAdapter()
        initRecyclerView()
        readContacts()
    }

    private fun setupRVAdapter(){
        rvAdapter = AddCustomerFromContactsRVAdapter()
        rvAdapter.changeChecked = {
            it.shouldAdd = !it.shouldAdd
        }
    }

    private fun initRecyclerView(){
        with(binding.rvContactsList){
            adapter = rvAdapter
            layoutManager=LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }

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
                    val name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val newContact = ContactsItem(
                        name = name,
                        phone = phone
                    )
                    listOfContacts.add(newContact)
                }
            }
            rvAdapter.submitList(listOfContacts)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}