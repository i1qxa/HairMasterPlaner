package com.example.hairmasterplaner.data.jobElement

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import com.example.hairmasterplaner.domain.jobElement.JobElementRepository

class JobElementRepositoryImpl(application: Application): JobElementRepository {

    private val mapper = JobElementMapper()
    private val dao = AppDatabase.getInstance(application).jobElementItemDBModelDao()

    override fun getJobElementList(): LiveData<List<JobElementItem>> {
        return Transformations.map(dao.getJobElementItemList()){
            mapper.mapListDBModelToListJobElementItem(it)
        }
    }

    override fun getServiceList(): LiveData<List<JobElementItem>> {
        return Transformations.map(dao.getServiceList()){
            mapper.mapListDBModelToListJobElementItem(it)
        }
    }

    override fun getMaterialList(): LiveData<List<JobElementItem>> {
        return Transformations.map(dao.getMaterialList()){
            mapper.mapListDBModelToListJobElementItem(it)
        }
    }

    override suspend fun getJobElementItem(id: Int): JobElementItem {
        return mapper.mapDBModelToJobElementItem(dao.getJobElementItem(id))
    }

    override suspend fun addJobElementItem(jobElementItem: JobElementItem) {
        dao.addJobElementItem(mapper.mapJobElementItemToDBModel(jobElementItem))
    }

    override suspend fun editJobElementItem(jobElementItem: JobElementItem) {
        dao.editJobElementItem(mapper.mapJobElementItemToDBModel(jobElementItem))
    }

    override suspend fun deleteJobElementItem(id: Int) {
        dao.deleteJobElementItem(id)
    }
}