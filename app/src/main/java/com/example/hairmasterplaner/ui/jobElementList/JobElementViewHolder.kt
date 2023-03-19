package com.example.hairmasterplaner.ui.jobElementList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hairmasterplaner.R

class JobElementViewHolder(itemView: View):ViewHolder(itemView) {
    val tvJobElementName = itemView.findViewById<TextView>(R.id.tvJobElementName)
    val tvId = itemView.findViewById<TextView>(R.id.tvId)
    val tvUnitOM = itemView.findViewById<TextView>(R.id.tvUnitOm)
    val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
}