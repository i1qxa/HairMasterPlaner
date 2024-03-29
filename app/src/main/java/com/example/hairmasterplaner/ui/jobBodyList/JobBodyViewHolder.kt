package com.example.hairmasterplaner.ui.jobBodyList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hairmasterplaner.R

class JobBodyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    val tvOrderNumber = itemView.findViewById<TextView>(R.id.tvOrderNumber)
    val tvJobElementItemName = itemView.findViewById<TextView>(R.id.tvJobElementItemName)
    val tvAmounOfItem = itemView.findViewById<TextView>(R.id.tvAmountOfItem)
    val tvPriceOfItem = itemView.findViewById<TextView>(R.id.tvPriceOfItem)
    val tvSumOfItem = itemView.findViewById<TextView>(R.id.tvSumOfItem)
}