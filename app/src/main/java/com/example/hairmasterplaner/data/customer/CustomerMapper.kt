package com.example.hairmasterplaner.data.customer

import com.example.hairmasterplaner.domain.customer.CustomerItem

class CustomerMapper {
    fun mapCustomerToDBModel(customer: CustomerItem): CustomerItemDBModel {
        return CustomerItemDBModel(
            id = customer.id,
            name = customer.name,
            telNumber = customer.telNumber
        )
    }
    fun mapDBModelToCustomer(customerDB: CustomerItemDBModel): CustomerItem {
        return CustomerItem(
            id = customerDB.id,
            name = customerDB.name,
            telNumber = customerDB.telNumber
        )
    }
    fun mapListDBModelToListCustomer(listDB:List<CustomerItemDBModel>) =
        listDB.map { mapDBModelToCustomer(it) }
}