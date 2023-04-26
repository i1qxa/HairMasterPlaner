package com.example.hairmasterplaner.ui.serviceList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.services.ServiceItem

class ServiceItemDiffCallBack() : DiffUtil.ItemCallback<ServiceItem>() {
    override fun areItemsTheSame(oldItem: ServiceItem, newItem: ServiceItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ServiceItem, newItem: ServiceItem): Boolean {
        return oldItem == newItem
    }
}