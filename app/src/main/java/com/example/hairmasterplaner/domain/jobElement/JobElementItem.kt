package com.example.hairmasterplaner.domain.jobElement

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobElementItem(
    val id: Int,
    val name: String,
    val isService: Boolean,
    val unitOM: String?,
    val price: Int?
) : Parcelable
