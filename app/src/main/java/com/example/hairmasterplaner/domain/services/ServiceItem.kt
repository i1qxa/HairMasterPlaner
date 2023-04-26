package com.example.hairmasterplaner.domain.services

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceItem(
    val id: Int,
    val name: String,
    val listMaterialItemIds:List<Int>,
    val price: Int
) : Parcelable
