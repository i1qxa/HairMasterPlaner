package com.example.hairmasterplaner.ui.serviceList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hairmasterplaner.R

class ServiceListViewHolder(itemView: View):ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvServiceName)
    val tvPrice = itemView.findViewById<TextView>(R.id.tvServicePrice)
}