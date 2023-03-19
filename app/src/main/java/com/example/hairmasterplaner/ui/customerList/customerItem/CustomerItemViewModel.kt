package com.example.hairmasterplaner.ui.customerList.customerItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.customer.CustomerRepositoryImpl
import com.example.hairmasterplaner.domain.customer.CustomerItem
import kotlinx.coroutines.launch

class CustomerItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CustomerRepositoryImpl(application)
    private var _isFinish = MutableLiveData<Boolean>()
    val isFinish: LiveData<Boolean>
        get() = _isFinish

    fun saveCustomer(id: Int = 0, name: String, phoneNumber: String) {
        if (validateName(name)) {
            val customerItem = CustomerItem(id, name, phoneNumber)
            if (id==0) addCustomerItem(customerItem)
            else editCustomerItem(customerItem)
            _isFinish.postValue(true)
        }
    }

    private fun addCustomerItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            repository.addCustomerItem(customerItem)
        }
    }

    private fun editCustomerItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            repository.editCustomerItem(customerItem)
        }
    }



    private fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }
}