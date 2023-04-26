package com.example.hairmasterplaner.domain.services

import androidx.lifecycle.LiveData

interface ServiceRepository {

    fun getServiceList(): LiveData<List<ServiceItem>>

    fun getServiceListWithMaterials():LiveData<List<ServiceItemWithMaterial>>

    suspend fun addServiceItem(serviceItem: ServiceItem)

    suspend fun editServiceItem(serviceItem: ServiceItem)

    suspend fun deleteServiceItem(id:Int)

}