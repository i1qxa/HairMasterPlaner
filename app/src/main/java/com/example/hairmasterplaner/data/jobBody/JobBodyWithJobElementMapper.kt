package com.example.hairmasterplaner.data.jobBody

import com.example.hairmasterplaner.data.jobElement.JobElementMapper
import com.example.hairmasterplaner.domain.jobBody.JobBodyWithJobElement

class JobBodyWithJobElementMapper {
    private val jobBodyMapper = JobBodyMapper()
    private val jobElementMapper = JobElementMapper()

    fun mapDBToJobBodyWithJobElement(itemDB:JobBodyItemWithJobElementItemDBModel):JobBodyWithJobElement{
        return JobBodyWithJobElement(
            jobBodyMapper.mapDBToJobBodyItem(itemDB.jobBodyItem),
            jobElementMapper.mapDBModelToJobElementItem(itemDB.jobElementItem)
        )
    }

    fun mapJobBodyWithJobElementToDB(item:JobBodyWithJobElement):JobBodyItemWithJobElementItemDBModel{
        return JobBodyItemWithJobElementItemDBModel(
            jobBodyMapper.mapJobBodyItemToDB(item.jobBodyItem),
            jobElementMapper.mapJobElementItemToDBModel(item.jobElementItem)
        )
    }

    fun mapListDBToListJobBodyWithJobElement(listDB:List<JobBodyItemWithJobElementItemDBModel>):List<JobBodyWithJobElement>{
        return listDB.map {
            mapDBToJobBodyWithJobElement(it)
        }
    }
}