package com.example.hairmasterplaner.data.job

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemFullInfo
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.domain.job.JobRepository

class JobItemRepositoryImpl(application: Application) : JobRepository {

    private val mapper = JobItemMapper()
    private val mapperJoin = JobItemWithCustomerMapper()
    private val dao = AppDatabase.getInstance(application).jobItemDBModelDao()

    override fun getJobListForCustomer(customerId: Int): LiveData<List<JobItemWithCustomer>> =
        dao.getJobListForCustomer(customerId).map {
            mapperJoin.mapListDBToListJobWithCustomer(it)
        }


//    override fun getJobListInDateRange(
//        dateStart: Long,
//        dateEnd: Long
//    ): LiveData<List<JobItemWithCustomer>> = dao.getJobListInDateRange(dateStart, dateEnd).map {
//        mapperJoin.mapListDBToListJobWithCustomer(it)
//    }

    override fun getJobFullInfoListInDateRange(
        dateStart: Long,
        dateEnd: Long
    ): LiveData<List<JobItemFullInfo>> = dao.getJobFullInfoListInDateRange(dateStart, dateEnd)

    override suspend fun getLastJobItemWithCustomer(): JobItemWithCustomer {
        return mapperJoin.mapDBToJobWithCustomer(dao.getLastJobItemWithCustomerDBModel())
    }

    override suspend fun getJobItemWithCustomer(id: Long): JobItemWithCustomer {
        return mapperJoin.mapDBToJobWithCustomer(dao.getJobItem(id))
    }

    override suspend fun addJobItem(jobItem: JobItem):Long {
       return dao.addJobItem(mapper.mapJobItemToDBModel(jobItem))
    }

    override suspend fun editJobItem(jobItem: JobItem) {
        dao.editJobItem(mapper.mapJobItemToDBModel(jobItem))
    }

    override suspend fun deleteJobItem(id: Long) {
        dao.deleteJobItem(id)
    }
}