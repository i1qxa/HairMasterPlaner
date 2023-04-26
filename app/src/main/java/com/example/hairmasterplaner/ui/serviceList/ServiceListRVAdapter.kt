package com.example.hairmasterplaner.ui.serviceList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.services.ServiceItem


class ServiceListRVAdapter() :
    ListAdapter<ServiceItem, ServiceListViewHolder>(ServiceItemDiffCallBack()) {

    var onItemClickListener: ((ServiceItem) -> Unit)? = null
    var onItemLongClickListener: ((ServiceItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.rv_service_item
        return ServiceListViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ServiceListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            val price = item.price?:0.0
            tvName.text = item.name
            tvPrice.text = "${price}р."
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            itemView.setOnLongClickListener {
                onItemLongClickListener?.invoke(item)
                true
            }
        }
    }
}