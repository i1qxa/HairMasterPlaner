package com.example.hairmasterplaner.domain.customer

import androidx.lifecycle.LiveData
import com.example.hairmasterplaner.domain.customer.CustomerItem

interface CustomerRepository {

    fun getCustomerList(): LiveData<List<CustomerItem>>

    suspend fun getCustomerItem(id: Int): CustomerItem

    suspend fun addCustomerItem(customerItem: CustomerItem)

    suspend fun editCustomerItem(customerItem: CustomerItem)

    suspend fun deleteCustomerItem(id:Int)

}