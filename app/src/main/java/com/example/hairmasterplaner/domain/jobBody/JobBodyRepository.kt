package com.example.hairmasterplaner.domain.jobBody

import androidx.lifecycle.LiveData
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem

interface JobBodyRepository {

    fun getJobBodyList(jodId:Long):LiveData<List<JobBodyItem>>

    fun getJobBodyWithJobElementList(jobId:Long):LiveData<List<JobBodyWithJobElement>>

    suspend fun addJobBodyItem(item: JobBodyItem)

    suspend fun deleteJobBodyItem(itemId:Long)

    suspend fun editJobBodyItem(item: JobBodyItem)

}