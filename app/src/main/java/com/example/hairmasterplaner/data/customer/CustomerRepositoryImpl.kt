package com.example.hairmasterplaner.data.customer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.customer.CustomerRepository

class CustomerRepositoryImpl(application: Application): CustomerRepository {

    private val mapper = CustomerMapper()
    private val customerItemDBModelDao = AppDatabase.getInstance(application).customerItemDBModelDao()

    override suspend fun getCustomerList(): LiveData<List<CustomerItem>> =
        Transformations.map(customerItemDBModelDao.getCustomerList()){
            mapper.mapListDBModelToListCustomer(it)
        }

    override suspend fun getCustomerItem(id: Int): CustomerItem =
        mapper.mapDBModelToCustomer(customerItemDBModelDao.getCustomerItem(id))


    override suspend fun addCustomerItem(customerItem: CustomerItem) {
        customerItemDBModelDao.addCustomerItem(mapper.mapCustomerToDBModel(customerItem))
    }

    override suspend fun editCustomerItem(customerItem: CustomerItem) {
        customerItemDBModelDao.editCustomerItem(mapper.mapCustomerToDBModel(customerItem))
    }

    override suspend fun deleteCustomerItem(id: Int) {
        customerItemDBModelDao.deleteCustomerItem(id)
    }
}