package com.example.hairmasterplaner.data.PriceRegister

import com.example.hairmasterplaner.data.services.ServiceMapper
import com.example.hairmasterplaner.domain.PriceRegister.PriceRegisterItem

class PriceRegisterMapper {
    val serviceMapper = ServiceMapper()
    fun mapPriceRegisterDBToPriceRegister(priceRegisterDB: PriceRegisterItemDBModel):
            PriceRegisterItem {
        return PriceRegisterItem(
            id = priceRegisterDB.id,
            jobElementId = priceRegisterDB.jobElementId,
            price = priceRegisterDB.price,
            date = priceRegisterDB.date
        )
    }
    fun mapPriceRegisterToPriceRegisterDB(priceRegister: PriceRegisterItem):
            PriceRegisterItemDBModel {
        return PriceRegisterItemDBModel(
            id = priceRegister.id,
            jobElementId = priceRegister.jobElementId,
            price = priceRegister.price,
            date = priceRegister.date
        )
    }
    fun mapListDBToListMaterialPriceRegisterItem(listDB:List<PriceRegisterItemDBModel>):
            List<PriceRegisterItem>{
        return listDB.map {
            mapPriceRegisterDBToPriceRegister(it)
        }
    }
}