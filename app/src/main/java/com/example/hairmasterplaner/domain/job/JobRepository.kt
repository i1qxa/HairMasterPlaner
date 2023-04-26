package com.example.hairmasterplaner.domain.job

import androidx.lifecycle.LiveData

interface JobRepository {

    fun getJobListForCustomer(customerId:Int):LiveData<List<JobItemFullInfo>>

    fun getJobFullInfoListInDateRange(dateStart: Long, dateEnd:Long):LiveData<List<JobItemFullInfo>>

    suspend fun getJobItemWithCustomer(id:Long): JobItemWithCustomer

    suspend fun addJobItem(jobItem: JobItem):Long

    suspend fun editJobItem(jobItem: JobItem)

    suspend fun deleteJobItem(id:Long)

}