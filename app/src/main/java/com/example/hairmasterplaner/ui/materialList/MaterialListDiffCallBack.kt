package com.example.hairmasterplaner.ui.materialList

import androidx.recyclerview.widget.DiffUtil
import com.example.hairmasterplaner.domain.materials.MaterialItem

class MaterialListDiffCallBack:DiffUtil.ItemCallback<MaterialItem>() {
    override fun areItemsTheSame(oldItem: MaterialItem, newItem: MaterialItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MaterialItem, newItem: MaterialItem): Boolean {
        return oldItem == newItem
    }
}