package com.example.hairmasterplaner.ui.jobList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.job.JobItemFullInfo

class JobListDiffCallback:DiffUtil.ItemCallback<JobItemFullInfo>() {
    override fun areItemsTheSame(
        oldItem: JobItemFullInfo,
        newItem: JobItemFullInfo
    ): Boolean {
        return oldItem.jobId == newItem.jobId
    }

    override fun areContentsTheSame(
        oldItem: JobItemFullInfo,
        newItem: JobItemFullInfo
    ): Boolean {
        return oldItem == newItem
    }
}