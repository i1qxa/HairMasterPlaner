package com.example.hairmasterplaner.data.PriceRegister

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.hairmasterplaner.data.jobElement.JobElementItemDBModel

@Entity(
    foreignKeys = [
        ForeignKey(
            entity =  JobElementItemDBModel::class,
            parentColumns = ["id"],
            childColumns = ["jobElementId"]
        )
    ]
)
data class PriceRegisterItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int,
    @ColumnInfo
    val jobElementId: Int,
    @ColumnInfo
    val price:Int,
    @ColumnInfo
    val date:String
)
