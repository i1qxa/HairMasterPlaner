package com.example.hairmasterplaner.ui.jobElementList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.jobElement.JobElementItem

class JobElementDiffCallBack() : DiffUtil.ItemCallback<JobElementItem>() {
    override fun areItemsTheSame(oldItem: JobElementItem, newItem: JobElementItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JobElementItem, newItem: JobElementItem): Boolean {
        return oldItem == newItem
    }
}