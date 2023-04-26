package com.example.hairmasterplaner.data.services

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ServiceItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val listMaterialItemIds:List<Int>,
    @ColumnInfo
    val price: Int,
)
