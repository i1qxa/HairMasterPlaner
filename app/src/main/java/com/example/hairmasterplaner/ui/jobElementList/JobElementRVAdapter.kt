package com.example.hairmasterplaner.ui.jobElementList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.jobElement.JobElementItem


class JobElementRVAdapter() :
    ListAdapter<JobElementItem, JobElementViewHolder>(JobElementDiffCallBack()) {

    var onItemClickListener: ((JobElementItem) -> Unit)? = null
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
        with(holder) {
            val price = item.price?:0.0
            tvId.text = item.id.toString()
            tvJobElementName.text = item.name
            tvPrice.text = "${price}р."
            if (getItemViewType(position) == ITEM_IS_MATERIAL) {
                tvUnitOM.visibility = View.VISIBLE
                tvUnitOM.text = item.unitOM
            }
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            itemView.setOnLongClickListener {
                onItemLongClickListener?.invoke(item)
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val itemIsService = getItem(position).isService
        return when (itemIsService) {
            true -> ITEM_IS_SERVICE
            false -> ITEM_IS_MATERIAL
        }
    }

    companion object {
        private const val ITEM_IS_SERVICE = 1
        private const val ITEM_IS_MATERIAL = 0
    }
}