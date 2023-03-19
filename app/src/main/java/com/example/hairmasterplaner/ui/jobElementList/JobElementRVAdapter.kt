package com.example.hairmasterplaner.ui.jobElementList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.jobElement.JobElementItem

class JobElementRVAdapter:ListAdapter<JobElementItem,JobElementViewHolder>(JobElementDiffCallBack()) {

    var onItemClickListener : ((JobElementItem) -> Unit)? = null
    var onItemLongClickListener: ((JobElementItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobElementViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.fragment_job_element_item
        return JobElementViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobElementViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvJobElementName.text = item.name
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.invoke(item)
            true
        }
    }
}