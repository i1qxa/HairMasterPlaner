package com.example.hairmasterplaner.ui.jobList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hairmasterplaner.R

class JobListViewHolder(itemView:View):ViewHolder(itemView) {
    val tvOrderNum = itemView.findViewById<TextView>(R.id.tvOrderNumJobList)
    val tvCustomerName = itemView.findViewById<TextView>(R.id.tvCustomerNameJobListItem)
    val tvDate = itemView.findViewById<TextView>(R.id.tvDateOfJob)
    val tvSumOfJob = itemView.findViewById<TextView>(R.id.tvSumOfJob)
}