package com.example.hairmasterplaner.ui.customerList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.customer.CustomerItem

class CustomerDiffCallBack:DiffUtil.ItemCallback<CustomerItem>() {
    override fun areItemsTheSame(oldItem: CustomerItem, newItem: CustomerItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CustomerItem, newItem: CustomerItem): Boolean {
        return oldItem == newItem
    }
}