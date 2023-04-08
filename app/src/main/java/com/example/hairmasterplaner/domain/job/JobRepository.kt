package com.example.hairmasterplaner.domain.job

import androidx.lifecycle.LiveData
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer

interface JobRepository {

    fun getJobListForCustomer(customerId:Int):LiveData<List<JobItemWithCustomer>>

    fun getJobListInDateRange(dateStart:Long, dateEnd:Long):LiveData<List<JobItemWithCustomer>>

    suspend fun getLastJobItemWithCustomer():JobItemWithCustomer

    suspend fun getJobItemWithCustomer(id:Long): JobItemWithCustomer

    suspend fun addJobItem(jobItem: JobItem)

    suspend fun editJobItem(jobItem: JobItem)

    suspend fun deleteJobItem(id:Long)

}