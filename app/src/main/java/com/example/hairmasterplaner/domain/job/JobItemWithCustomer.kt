package com.example.hairmasterplaner.domain.job

import android.os.Parcelable
import com.example.hairmasterplaner.domain.customer.CustomerItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobItemWithCustomer(
    val jobItem: JobItem,
    val customerItem: CustomerItem,
):Parcelable
