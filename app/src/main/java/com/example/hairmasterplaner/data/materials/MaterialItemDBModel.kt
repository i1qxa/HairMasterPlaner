package com.example.hairmasterplaner.data.materials

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MaterialItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val purchasePrice: Int,
    @ColumnInfo
    val rtlPrice: Int,
)
