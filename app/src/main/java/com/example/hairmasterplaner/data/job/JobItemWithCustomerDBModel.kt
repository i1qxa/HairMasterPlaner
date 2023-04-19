package com.example.hairmasterplaner.data.job

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hairmasterplaner.data.customer.CustomerItemDBModel

data class JobItemWithCustomerDBModel(
    @Embedded
    val jobItemDBModel: JobItemDBModel,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customerItemDBModel: CustomerItemDBModel?
)
