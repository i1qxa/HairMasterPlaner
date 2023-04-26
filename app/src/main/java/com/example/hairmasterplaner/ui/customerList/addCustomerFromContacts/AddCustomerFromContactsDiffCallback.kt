package com.example.hairmasterplaner.ui.customerList.addCustomerFromContacts

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.customer.ContactsItem

class AddCustomerFromContactsDiffCallback:DiffUtil.ItemCallback<ContactsItem>() {
    override fun areItemsTheSame(oldItem: ContactsItem, newItem: ContactsItem): Boolean {
        return oldItem.phone == newItem.phone
    }

    override fun areContentsTheSame(oldItem: ContactsItem, newItem: ContactsItem): Boolean {
        return oldItem == newItem
    }
}