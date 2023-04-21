package com.example.hairmasterplaner.data.job

import com.example.hairmasterplaner.data.customer.CustomerMapper
import com.example.hairmasterplaner.domain.job.JobItemFullInfo
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer

class JobItemWithCustomerMapper {

    private val customerMapper = CustomerMapper()
    private val jobItemMapper = JobItemMapper()

    fun mapDBToJobWithCustomer(dbItem: JobItemWithCustomerDBModel): JobItemWithCustomer {
        return JobItemWithCustomer(
            jobItem = jobItemMapper.mapDBModelToJobItem(dbItem.jobItemDBModel),
            customerItem = dbItem.customerItemDBModel?.let { customerMapper.mapDBModelToCustomer(it) }
        )
    }

//    fun mapDBToJobItemFullInfo(dbItem: JobItemFullInfoDBModel): JobItemFullInfo {
//        return JobItemFullInfo(
//            jobId = jobItemMapper.mapDBModelToJobItem(dbItem.jobItemDBModel),
//            customerItem = dbItem.customerItemDB?.let { customerMapper.mapDBModelToCustomer(it) },
//            totalSum = dbItem.totalSumDB
//        )
//    }

    fun mapJobWithCustomerToDB(item: JobItemWithCustomer): JobItemWithCustomerDBModel {
        return JobItemWithCustomerDBModel(
            jobItemDBModel = jobItemMapper.mapJobItemToDBModel(item.jobItem),
            customerItemDBModel = item.customerItem?.let { customerMapper.mapCustomerToDBModel(it) }
        )
    }

//    fun mapListDBFullInfo(listDB:List<JobItemFullInfoDBModel>):List<JobItemFullInfo>{
//        return listDB.map {
//            mapDBToJobItemFullInfo(it)
//        }
//    }

    fun mapListDBToListJobWithCustomer(listDB: List<JobItemWithCustomerDBModel>): List<JobItemWithCustomer> {
        return listDB.map {
            mapDBToJobWithCustomer(it)
        }
    }

}