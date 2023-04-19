package com.example.hairmasterplaner.ui.jobList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer

class JobListDiffCallback:DiffUtil.ItemCallback<JobItemWithCustomer>() {
    override fun areItemsTheSame(
        oldItem: JobItemWithCustomer,
        newItem: JobItemWithCustomer
    ): Boolean {
        return oldItem.jobItem.id == newItem.jobItem.id
    }

    override fun areContentsTheSame(
        oldItem: JobItemWithCustomer,
        newItem: JobItemWithCustomer
    ): Boolean {
        return oldItem == newItem
    }
}