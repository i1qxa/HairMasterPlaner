package com.example.hairmasterplaner.ui.materialList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hairmasterplaner.R

class MaterialListViewHolder(itemView:View):ViewHolder(itemView) {
    val tvMaterialName = itemView.findViewById<TextView>(R.id.tvMaterialName)
    val tvMaterialPurchasePrice = itemView.findViewById<TextView>(R.id.tvMaterialPurchasePrice)
    val tvMaterialRtlPrice = itemView.findViewById<TextView>(R.id.etMaterialRtlPrice)
}