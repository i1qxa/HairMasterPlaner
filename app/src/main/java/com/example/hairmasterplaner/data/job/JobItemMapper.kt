package com.example.hairmasterplaner.data.job

import com.example.hairmasterplaner.domain.job.JobItem

class JobItemMapper {
    fun mapJobItemToDBModel(jobItem: JobItem): JobItemDBModel {
        return JobItemDBModel(
            id = jobItem.id,
            date = jobItem.date,
            customerId = jobItem.customerId,
        )
    }

    fun mapDBModelToJobItem(jobItemDB: JobItemDBModel): JobItem {
        return JobItem(
            id = jobItemDB.id,
            date = jobItemDB.date,
            customerId = jobItemDB.customerId
        )
    }

    fun mapListDBModelToListJobItem(listDB: List<JobItemDBModel>): List<JobItem> {
        return listDB.map {
            mapDBModelToJobItem(it)
        }
    }

}