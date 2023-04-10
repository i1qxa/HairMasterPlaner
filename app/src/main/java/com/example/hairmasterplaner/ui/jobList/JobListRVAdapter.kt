package com.example.hairmasterplaner.ui.jobList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.toDateTime

class JobListRVAdapter:ListAdapter<JobItemWithCustomer, JobListViewHolder>(JobListDiffCallback()) {

    var onItemClickListener: ((JobItemWithCustomer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        val layout = R.layout.job_list_rv_item
        val layoutInflater = LayoutInflater.from(parent.context)
        return JobListViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvOrderNum.text = position.toString()
            tvCustomerName.text = item.customerItem?.name
            tvDate.text = item.jobItem.dateInMils.toDateTime(false)
            tvSumOfJob.text = "5490"//нужно реализовать подсчет суммы всего заказа, в идеале одной выборкой из БД
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}