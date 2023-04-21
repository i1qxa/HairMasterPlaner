package com.example.hairmasterplaner.domain.job

data class JobItemFullInfo(
    val jobId:Long,
    val dateInMils:Long,
    val customerName:String?,
    val totalSum:Int = 0,
)
