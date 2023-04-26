package com.example.hairmasterplaner.ui.customerList.addCustomerFromContacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.customer.CustomerRepositoryImpl
import com.example.hairmasterplaner.domain.customer.ContactsItem
import kotlinx.coroutines.launch

class AddCustomerFromContactsViewModel(application: Application):AndroidViewModel(application) {

    //TODO("Не работает фильтрация уже существующих клиентов")

    private val repository = CustomerRepositoryImpl(application)

    private val customerPhoneList = mutableListOf<String>()
    private val customerPhoneListLD = repository.getCustomerList()
    private val contactsList = mutableListOf<ContactsItem>()
    private val _contactsListLD = MutableLiveData<List<ContactsItem>>()
    val contactsLisLD:LiveData<List<ContactsItem>>
        get() = _contactsListLD
    private val _shouldFinishWork = MutableLiveData<Int>()
    val shouldFinishWork:LiveData<Int>
        get() = _shouldFinishWork

    init {
        customerPhoneListLD.observeForever { customerList ->
            customerList.map { customerItem ->
                customerPhoneList.add(customerItem.telNumber)
            }
        }
    }

    fun filterContactsList(listContacts:List<ContactsItem>){
        listContacts.forEach { contactItem ->
            if (!customerPhoneList.contains(contactItem.phone)){
                contactsList.add(contactItem)
            }
        }
        _contactsListLD.value = contactsList
    }

    fun addContactsToDB(){
        val contactsList = _contactsListLD.value?.filter { contactItem ->
            contactItem.shouldAdd
        }
        viewModelScope.launch {
            contactsList?.let { contactsList ->
                repository.addListContacts(contactsList)
                _shouldFinishWork.postValue(contactsList.size)
            }
        }
    }
}