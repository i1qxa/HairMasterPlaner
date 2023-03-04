package com.example.hairmasterplaner.domain.jobBody

data class JobBodyItem(
    val id:Long,
    val jobId:Int,
    val jobElementItemId: Int,
    val amount:Int?,
    val price:Int,
)