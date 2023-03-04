package com.example.hairmasterplaner.data.job

import com.example.hairmasterplaner.data.customer.CustomerMapper
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer

class JobItemWithCustomerMapper {

    private val customerMapper = CustomerMapper()
    private val jobItemMapper = JobItemMapper()

    fun mapDBToJobWithCustomer(dbItem: JobItemWithCustomerDBModel): JobItemWithCustomer {
        return JobItemWithCustomer(
            jobItem = jobItemMapper.mapDBModelToJobItem(dbItem.jobItemDBModel),
            customerItem = customerMapper.mapDBModelToCustomer(dbItem.customerItemDBModel)
        )
    }
    fun mapJobWithCustomerToDB(item: JobItemWithCustomer): JobItemWithCustomerDBModel {
        return JobItemWithCustomerDBModel(
            jobItemDBModel = jobItemMapper.mapJobItemToDBModel(item.jobItem),
            customerItemDBModel = customerMapper.mapCustomerToDBModel(item.customerItem)
        )
    }
    fun mapListDBToListJobWithCustomer(listDB:List<JobItemWithCustomerDBModel>):List<JobItemWithCustomer>{
        return listDB.map {
            mapDBToJobWithCustomer(it)
        }
    }

}