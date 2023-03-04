package com.example.hairmasterplaner.data.jobBody

import com.example.hairmasterplaner.domain.jobBody.JobBodyItem

class JobBodyMapper {
    fun mapDBToJobBodyItem(jobBodyDB: JobBodyItemDBModel): JobBodyItem {
        return JobBodyItem(
            id = jobBodyDB.id,
            jobId = jobBodyDB.jobId,
            jobElementItemId = jobBodyDB.jobElementId,
            amount = jobBodyDB.amount,
            price = jobBodyDB.price
        )
    }
    fun mapJobBodyItemToDB(jobBody: JobBodyItem): JobBodyItemDBModel {
        return JobBodyItemDBModel(
            id = jobBody.id,
            jobId = jobBody.jobId,
            jobElementId = jobBody.jobElementItemId,
            amount = jobBody.amount,
            price = jobBody.price
        )
    }
    fun mapListDBToListJobBodyItem(listDB:List<JobBodyItemDBModel>):List<JobBodyItem>{
        return listDB.map {
            mapDBToJobBodyItem(it)
        }
    }
}