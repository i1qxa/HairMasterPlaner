package com.example.hairmasterplaner.domain.PriceRegister

import androidx.lifecycle.LiveData

interface PriceRegisterRepository {

    suspend fun getPriceRegisterListInDateRange(dateStart:String, dateEnd:String): LiveData<List<PriceRegisterItem>>

    suspend fun getLastPriceAtDate(date:String, jobElementId: Int):Int

    suspend fun addPriceRegisterRecord(record: PriceRegisterItem)

    suspend fun editPriceRegisterRecord(record: PriceRegisterItem)

    suspend fun deletePriceRegisterRecord(recordId:Int)

}