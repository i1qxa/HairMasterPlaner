package com.example.hairmasterplaner.data.jobBody

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hairmasterplaner.data.jobElement.JobElementItemDBModel

data class JobBodyItemWithJobElementItemDBModel(
    @Embedded
    val jobBodyItem: JobBodyItemDBModel,
    @Relation(
        parentColumn = "jobElementId",
        entityColumn = "id"
    )
    val jobElementItem: JobElementItemDBModel
)
