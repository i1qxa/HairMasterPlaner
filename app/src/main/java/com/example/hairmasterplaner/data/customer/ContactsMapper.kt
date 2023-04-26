package com.example.hairmasterplaner.data.customer

import com.example.hairmasterplaner.domain.customer.ContactsItem

class ContactsMapper {
    fun mapContactToCustomerItem(contact:ContactsItem):CustomerItemDBModel{
        return CustomerItemDBModel(
            id = 0,
            name = contact.name?:"",
            telNumber = contact.phone?:"",
        )
    }
    fun mapListContactsToListCustomer(listContacts:List<ContactsItem>):List<CustomerItemDBModel>{
        return listContacts.map { contactItem ->
            mapContactToCustomerItem(contactItem)
        }
    }
}