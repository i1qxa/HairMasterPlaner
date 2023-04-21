package com.example.hairmasterplaner.data.job

import com.example.hairmasterplaner.data.customer.CustomerItemDBModel

data class JobItemFullInfoDBModel(
    val jobItemDBModel: JobItemDBModel,
    val customerItemDB:CustomerItemDBModel?,
    val totalSumDB:Int = 0,
)
