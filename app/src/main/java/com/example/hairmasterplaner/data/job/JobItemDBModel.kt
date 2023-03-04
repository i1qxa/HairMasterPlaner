package com.example.hairmasterplaner.data.job

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.hairmasterplaner.data.customer.CustomerItemDBModel

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CustomerItemDBModel::class,
            parentColumns = ["id"],
            childColumns = ["customerId"]
        )
    ]
)
data class JobItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id : Int,
    @ColumnInfo
    val date : String,
    @ColumnInfo
    val customerId : Int,
)
