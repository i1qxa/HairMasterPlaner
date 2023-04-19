package com.example.hairmasterplaner.domain.jobBody

data class JobBodyItem(
    val id:Long,
    val jobId:Long,
    val jobElementItemId: Int,
    val amount:Int?,
    val price:Int,
){
    fun getSum():Int{
        return (amount?:0) * price
    }
}