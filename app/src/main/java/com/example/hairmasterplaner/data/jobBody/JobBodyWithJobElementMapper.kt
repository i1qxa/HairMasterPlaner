package com.example.hairmasterplaner.data.jobBody

import com.example.hairmasterplaner.data.services.ServiceMapper
import com.example.hairmasterplaner.domain.jobBody.JobBodyWithJobElement

class JobBodyWithJobElementMapper {
    private val jobBodyMapper = JobBodyMapper()
    private val serviceMapper = ServiceMapper()

    fun mapDBToJobBodyWithJobElement(itemDB:JobBodyItemWithJobElementItemDBModel):JobBodyWithJobElement{
        return JobBodyWithJobElement(
            jobBodyMapper.mapDBToJobBodyItem(itemDB.jobBodyItem),
            serviceMapper.mapDBToServiceItem(itemDB.jobElementItem)
        )
    }

    fun mapJobBodyWithJobElementToDB(item:JobBodyWithJobElement):JobBodyItemWithJobElementItemDBModel{
        return JobBodyItemWithJobElementItemDBModel(
            jobBodyMapper.mapJobBodyItemToDB(item.jobBodyItem),
            serviceMapper.mapServiceItemTODB(item.serviceItem)
        )
    }

    fun mapListDBToListJobBodyWithJobElement(listDB:List<JobBodyItemWithJobElementItemDBModel>):List<JobBodyWithJobElement>{
        return listDB.map {
            mapDBToJobBodyWithJobElement(it)
        }
    }
}