package com.example.hairmasterplaner.domain.jobElement

import androidx.lifecycle.LiveData
import com.example.hairmasterplaner.domain.jobElement.JobElementItem

interface JobElementRepository {

    fun getJobElementList(): LiveData<List<JobElementItem>>

    fun getServiceList():LiveData<List<JobElementItem>>

    fun getMaterialList():LiveData<List<JobElementItem>>

    suspend fun getJobElementItem(id:Int): JobElementItem

    suspend fun addJobElementItem(jobElementItem: JobElementItem)

    suspend fun editJobElementItem(jobElementItem: JobElementItem)

    suspend fun deleteJobElementItem(id:Int)

}