package com.example.hairmasterplaner.ui.customerList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.customer.CustomerRepositoryImpl
import com.example.hairmasterplaner.domain.customer.CustomerItem
import kotlinx.coroutines.launch

class CustomerListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CustomerRepositoryImpl(application)

    private var _customerList = repository.getCustomerList()
    val customerList:LiveData<List<CustomerItem>>
    get() = _customerList

    fun deleteCustomerItem(itemId:Int){
        viewModelScope.launch {
            repository.deleteCustomerItem(itemId)
        }
    }

}