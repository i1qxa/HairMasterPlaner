package com.example.hairmasterplaner.data.jobBody

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.hairmasterplaner.data.job.JobItemDBModel
import com.example.hairmasterplaner.data.services.ServiceItemDBModel

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = JobItemDBModel::class,
            parentColumns = ["id"],
            childColumns = ["jobId"]
        ),
    ForeignKey(
        entity = ServiceItemDBModel::class,
        parentColumns = ["id"],
        childColumns = ["jobElementId"]
    )
    ]
)
data class JobBodyItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Long,
    @ColumnInfo
    val jobId:Long,
    @ColumnInfo
    val jobElementId: Int,
    @ColumnInfo
    val amount:Int?,
    @ColumnInfo
    val price:Int,
    )
