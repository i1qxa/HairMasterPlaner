package com.example.hairmasterplaner.domain.jobBody

import androidx.lifecycle.LiveData
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem

interface JobBodyRepository {

    suspend fun getJobBodyList(jodId:Int):LiveData<List<JobBodyItem>>

    suspend fun addJobBodyItem(item: JobBodyItem)

    suspend fun deleteJobBodyItem(itemId:Int)

    suspend fun editJobBodyItem(item: JobBodyItem)

}