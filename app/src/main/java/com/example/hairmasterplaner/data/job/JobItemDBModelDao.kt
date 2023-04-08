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

    @Query("SELECT * FROM jobitemdbmodel WHERE dateInMils >= :dateStart AND dateInMils <= :dateEnd")
    fun getJobListInDateRange(dateStart:Long, dateEnd:Long): LiveData<List<JobItemWithCustomerDBModel>>


    @Query("SELECT * FROM jobitemdbmodel ORDER BY dateInMils DESC LIMIT 1")
    suspend fun getLastJobItemWithCustomerDBModel():JobItemWithCustomerDBModel

    @Query("SELECT * FROM jobitemdbmodel WHERE id = :id")
    suspend fun getJobItem(id:Long): JobItemWithCustomerDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobItem(jobItem: JobItemDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editJobItem(jobItem: JobItemDBModel)

    @Query("DELETE FROM jobitemdbmodel WHERE id = :id")
    suspend fun deleteJobItem(id:Long)

}