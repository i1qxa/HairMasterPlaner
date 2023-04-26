package com.example.hairmasterplaner.data.services

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.services.ServiceItem
import com.example.hairmasterplaner.domain.services.ServiceItemWithMaterial
import com.example.hairmasterplaner.domain.services.ServiceRepository

class ServiceRepositoryImpl(application: Application) : ServiceRepository {

    private val mapper = ServiceMapper()
    private val dao = AppDatabase.getInstance(application).jobElementItemDBModelDao()


    override fun getServiceList(): LiveData<List<ServiceItem>> {
        return dao.getServiceList().map {
            mapper.mapListDBToListServiceItem(it)
        }
    }

    override fun getServiceListWithMaterials(): LiveData<List<ServiceItemWithMaterial>> {
        TODO("Not yet implemented")
    }

    override suspend fun addServiceItem(serviceItem: ServiceItem) {
        dao.addJobElementItem(mapper.mapServiceItemTODB(serviceItem))
    }

    override suspend fun editServiceItem(serviceItem: ServiceItem) {
        dao.editJobElementItem(mapper.mapServiceItemTODB(serviceItem))
    }

    override suspend fun deleteServiceItem(id: Int) {
        dao.deleteJobElementItem(id)
    }
}