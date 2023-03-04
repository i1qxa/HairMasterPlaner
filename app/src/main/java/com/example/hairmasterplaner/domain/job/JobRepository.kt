package com.example.hairmasterplaner.domain.job

import androidx.lifecycle.LiveData
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer

interface JobRepository {

    suspend fun getJobListForCustomer(customerId:Int):LiveData<List<JobItemWithCustomer>>

    suspend fun getJobListInDateRange(dateStart:String, dateEnd:String):LiveData<List<JobItemWithCustomer>>

    suspend fun getJobItemWithCustomer(id:Int): JobItemWithCustomer

    suspend fun addJobItem(jobItem: JobItem)

    suspend fun editJobItem(jobItem: JobItem)

    suspend fun deleteJobItem(id:Int)

}