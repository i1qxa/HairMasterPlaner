package com.example.hairmasterplaner.data.job

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JobItemDBModelDao {

    @Query("SELECT * FROM jobitemdbmodel WHERE customerId = :customerId")
    fun getJobListForCustomer(customerId:Int): LiveData<List<JobItemWithCustomerDBModel>>

    @Query("SELECT * FROM jobitemdbmodel WHERE date >= :dateStart AND date <= :dateEnd")
    fun getJobListInDateRange(dateStart:String, dateEnd:String): LiveData<List<JobItemWithCustomerDBModel>>

    @Query("SELECT * FROM jobitemdbmodel WHERE id = :id")
    suspend fun getJobItem(id:Int): JobItemWithCustomerDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobItem(jobItem: JobItemDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editJobItem(jobItem: JobItemDBModel)

    @Query("DELETE FROM jobitemdbmodel WHERE id = :id")
    suspend fun deleteJobItem(id:Int)

}