package com.example.hairmasterplaner.data.services

import com.example.hairmasterplaner.domain.services.ServiceItem

class ServiceMapper {
    fun mapServiceItemTODB(serviceItem: ServiceItem): ServiceItemDBModel {
        return ServiceItemDBModel(
            id = serviceItem.id,
            name = serviceItem.name,
            listMaterialItemIds = serviceItem.listMaterialItemIds,
            price = serviceItem.price,
        )
    }

    fun mapDBToServiceItem(serviceItemDB: ServiceItemDBModel): ServiceItem {
        return ServiceItem(
            id = serviceItemDB.id,
            name = serviceItemDB.name,
            listMaterialItemIds = serviceItemDB.listMaterialItemIds,
            price = serviceItemDB.price
        )
    }

    fun mapListDBToListServiceItem(listDB:List<ServiceItemDBModel>) =
        listDB.map { mapDBToServiceItem(it) }
}