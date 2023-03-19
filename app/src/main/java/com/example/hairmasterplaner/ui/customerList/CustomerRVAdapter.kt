package com.example.hairmasterplaner.ui.customerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.customer.CustomerItem

class CustomerRVAdapter:ListAdapter<CustomerItem,CustomerViewHolder>(CustomerDiffCallBack()) {

    var onItemClickListener : ((CustomerItem) -> Unit)? = null
    var onItemLongClickListener : ((CustomerItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.fragment_customer_item
        return CustomerViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvCustomerName.text = item.name
            tvPhoneNumber.text = item.telNumber
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            itemView.setOnLongClickListener {
                onItemLongClickListener?.invoke(item)
                true
            }
        }
    }
}