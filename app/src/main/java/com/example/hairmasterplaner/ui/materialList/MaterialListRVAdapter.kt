package com.example.hairmasterplaner.ui.materialList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.materials.MaterialItem

class MaterialListRVAdapter:ListAdapter<MaterialItem, MaterialListViewHolder>(MaterialListDiffCallBack()) {

    var onItemClickListener: ((MaterialItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialListViewHolder {
        val layout = R.layout.material_rv_item
        val layoutInflater = LayoutInflater.from(parent.context)
        return MaterialListViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MaterialListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvMaterialName.text = item.name
            tvMaterialPurchasePrice.text = item.purchasePrice.toString()
            tvMaterialRtlPrice.text = item.rtlPrice.toString()
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}