package com.example.hairmasterplaner.data.jobElement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobElementItemDBModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val isService: Boolean,
    @ColumnInfo
    val unitOM: String?,
    @ColumnInfo
    val price: Int?,
)
