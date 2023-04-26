package com.example.hairmasterplaner.data.services

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ServiceDBModelDao {


    @Query("SELECT * FROM ServiceItemDBModel")
    fun getServiceList(): LiveData<List<ServiceItemDBModel>>

//    @Query("SELECT * FROM serviceitemdbmodel as service LEFT JOIN materialitemdbmodel as material ON " +
//            "service.listMaterialItemIds")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobElementItem(serviceItem: ServiceItemDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editJobElementItem(serviceItem: ServiceItemDBModel)

    @Query("DELETE FROM ServiceItemDBModel WHERE id = :id")
    suspend fun deleteJobElementItem(id: Int)

}