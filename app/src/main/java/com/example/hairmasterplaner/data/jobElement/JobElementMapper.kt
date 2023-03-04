package com.example.hairmasterplaner.data.jobElement

import com.example.hairmasterplaner.domain.jobElement.JobElementItem

class JobElementMapper {
    fun mapJobElementItemToDBModel(jobElementItem: JobElementItem): JobElementItemDBModel {
        return JobElementItemDBModel(
            id = jobElementItem.id,
            name = jobElementItem.name,
            isService = jobElementItem.isService,
            unitOM = jobElementItem.unitOM,
        )
    }

    fun mapDBModelToJobElementItem(jobElementItemDB: JobElementItemDBModel): JobElementItem {
        return JobElementItem(
            id = jobElementItemDB.id,
            name = jobElementItemDB.name,
            isService = jobElementItemDB.isService,
            unitOM = jobElementItemDB.unitOM,
        )
    }

    fun mapListDBModelToListJobElementItem(listDB:List<JobElementItemDBModel>) =
        listDB.map { mapDBModelToJobElementItem(it) }
}