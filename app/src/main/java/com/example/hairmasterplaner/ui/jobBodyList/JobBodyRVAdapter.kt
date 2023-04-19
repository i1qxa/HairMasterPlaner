package com.example.hairmasterplaner.ui.jobBodyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem
import com.example.hairmasterplaner.domain.jobBody.JobBodyWithJobElement

class JobBodyRVAdapter:ListAdapter<JobBodyWithJobElement, JobBodyViewHolder>(JobBodyDiffCallback()) {

    var onItemClickListener: ((JobBodyItem) -> Unit)? = null
    var onAmountClickListener: ((JobBodyItem) -> Unit)? = null
    var onPriceClickListener: ((JobBodyItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobBodyViewHolder {
        val layout = R.layout.job_body_item
        val layoutInflater = LayoutInflater.from(parent.context)
        return JobBodyViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobBodyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvOrderNumber.text = position.toString()
            tvJobElementItemName.text = item.jobElementItem.name
            tvAmounOfItem.text = item.jobBodyItem.amount.toString()
            tvPriceOfItem.text = item.jobBodyItem.price.toString()
            tvSumOfItem.text = item.jobBodyItem.getSum().toString()
            tvAmounOfItem.setOnClickListener {
                onAmountClickListener?.invoke(item.jobBodyItem)
            }
            tvPriceOfItem.setOnClickListener {
                onPriceClickListener?.invoke(item.jobBodyItem)
            }
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item.jobBodyItem)
            }
        }

    }
}