package com.example.hairmasterplaner.ui.customerList.addCustomerFromContacts

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hairmasterplaner.R

class AddCustomerFromContactsViewHolder(itemView:View):ViewHolder(itemView) {

    val tvName = itemView.findViewById<TextView>(R.id.tvContactName)
    val tvPhone = itemView.findViewById<TextView>(R.id.tvContactPhone)
    val cbShouldAdd = itemView.findViewById<CheckBox>(R.id.cbShouldAdd)

}