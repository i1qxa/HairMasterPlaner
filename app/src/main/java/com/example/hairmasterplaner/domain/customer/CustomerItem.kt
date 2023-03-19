package com.example.hairmasterplaner.domain.customer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomerItem(
    val id : Int,
    val name : String,
    val telNumber : String,
):Parcelable