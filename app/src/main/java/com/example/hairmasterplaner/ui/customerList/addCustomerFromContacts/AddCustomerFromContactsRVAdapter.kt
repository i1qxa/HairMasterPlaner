package com.example.hairmasterplaner.ui.customerList.addCustomerFromContacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.customer.ContactsItem

class AddCustomerFromContactsRVAdapter:ListAdapter<ContactsItem, AddCustomerFromContactsViewHolder>(AddCustomerFromContactsDiffCallback()) {

    var changeChecked: ((ContactsItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddCustomerFromContactsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.contacts_item
        return AddCustomerFromContactsViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: AddCustomerFromContactsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvName.text = item.name
            tvPhone.text = item.phone
            cbShouldAdd.isChecked = item.shouldAdd
            cbShouldAdd.setOnClickListener {
                changeChecked?.invoke(item)
            }
        }
    }
}