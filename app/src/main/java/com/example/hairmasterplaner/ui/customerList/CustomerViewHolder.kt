package com.example.hairmasterplaner.ui.customerList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hairmasterplaner.R

class CustomerViewHolder(itemView: View):ViewHolder(itemView) {
    val tvCustomerName = itemView.findViewById<TextView>(R.id.tvName)
    val tvPhoneNumber = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
}