package com.example.hairmasterplaner.data.customer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id : Int,
    @ColumnInfo
    val name : String,
    @ColumnInfo
    val telNumber : Int,
)
