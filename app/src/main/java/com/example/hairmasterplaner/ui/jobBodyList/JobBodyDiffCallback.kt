package com.example.hairmasterplaner.ui.jobBodyList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.jobBody.JobBodyWithJobElement

class JobBodyDiffCallback:DiffUtil.ItemCallback<JobBodyWithJobElement>() {
    override fun areItemsTheSame(
        oldItem: JobBodyWithJobElement,
        newItem: JobBodyWithJobElement
    ): Boolean {
        return oldItem.jobBodyItem.id == newItem.jobBodyItem.id
    }

    override fun areContentsTheSame(
        oldItem: JobBodyWithJobElement,
        newItem: JobBodyWithJobElement
    ): Boolean {
        return oldItem == newItem
    }
}