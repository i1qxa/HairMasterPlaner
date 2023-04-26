package com.example.hairmasterplaner.domain.materials

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialItem(
    val id:Int,
    val name:String,
    val purchasePrice:Int,
    val rtlPrice:Int,
):Parcelable
