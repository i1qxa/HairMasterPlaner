package com.example.hairmasterplaner.data.jobBody

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem
import com.example.hairmasterplaner.domain.jobBody.JobBodyRepository
import com.example.hairmasterplaner.domain.jobBody.JobBodyWithJobElement

class JobBodyRepositoryImpl(application: Application):JobBodyRepository {

    private val mapper = JobBodyMapper()
    private val jobBodyWithJobElementMapper = JobBodyWithJobElementMapper()
    private val dao = AppDatabase.getInstance(application).jobBodyItemDBModelDao()

    override fun getJobBodyList(jodId: Long): LiveData<List<JobBodyItem>> {
        return Transformations.map(dao.getJobBodyList(jodId)){
            mapper.mapListDBToListJobBodyItem(it)
        }
    }

    override fun getJobBodyWithJobElementList(jobId: Long): LiveData<List<JobBodyWithJobElement>> {
        return Transformations.map(dao.getJobBodyWithJobElementList(jobId)){
            jobBodyWithJobElementMapper.mapListDBToListJobBodyWithJobElement(it)
        }
    }

    override suspend fun addJobBodyItem(item: JobBodyItem) {
        dao.addJobBodyItem(mapper.mapJobBodyItemToDB(item))
    }

    override suspend fun deleteJobBodyItem(itemId: Long) {
        dao.deleteJobBodyItem(itemId)
    }

    override suspend fun editJobBodyItem(item: JobBodyItem) {
        dao.editJobBodyItem(mapper.mapJobBodyItemToDB(item))
    }
}