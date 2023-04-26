package com.example.hairmasterplaner.data.customer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.customer.ContactsItem
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.customer.CustomerRepository

class CustomerRepositoryImpl(application: Application): CustomerRepository {

    private val mapper = CustomerMapper()
    private val contactsMapper = ContactsMapper()
    private val customerItemDBModelDao = AppDatabase.getInstance(application).customerItemDBModelDao()

    override fun getCustomerList(): LiveData<List<CustomerItem>> =
        customerItemDBModelDao.getCustomerList().map {
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

    override suspend fun addListContacts(contactsList: List<ContactsItem>) {
        customerItemDBModelDao.addListCustomer(contactsMapper.mapListContactsToListCustomer(contactsList))
    }
}