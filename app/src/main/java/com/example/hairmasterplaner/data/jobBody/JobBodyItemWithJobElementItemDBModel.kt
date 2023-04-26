package com.example.hairmasterplaner.data.jobBody

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hairmasterplaner.data.services.ServiceItemDBModel

data class JobBodyItemWithJobElementItemDBModel(
    @Embedded
    val jobBodyItem: JobBodyItemDBModel,
    @Relation(
        parentColumn = "jobElementId",
        entityColumn = "id"
    )
    val jobElementItem: ServiceItemDBModel
)
