package com.example.hairmasterplaner.data.job

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hairmasterplaner.domain.job.DateRange
import com.example.hairmasterplaner.domain.job.JobItemFullInfo

@Dao
interface JobItemDBModelDao {

    @Query("SELECT * FROM jobitemdbmodel WHERE customerId = :customerId")
    fun getJobListForCustomer(customerId:Int): LiveData<List<JobItemWithCustomerDBModel>>

    @Query("SELECT * FROM jobitemdbmodel WHERE dateInMils >= :dateStart AND dateInMils <= :dateEnd")
    fun getJobListInDateRange(dateStart:Long, dateEnd:Long): LiveData<List<JobItemWithCustomerDBModel>>

    @Query("SELECT job.id AS jobId, job.dateInMils AS dateInMils, customer.name AS customerName, SUM(body.amount * body.price) AS totalSum " +
            "FROM jobitemdbmodel AS job " +
            "LEFT JOIN customeritemdbmodel AS customer ON job.customerId = customer.id " +
            "LEFT JOIN jobbodyitemdbmodel AS body ON job.id = body.jobId " +
            "WHERE job.dateInMils >= :dateStart AND job.dateInMils <= :dateEnd " +
            "GROUP BY job.id")
    fun getJobFullInfoListInDateRange(dateStart:Long, dateEnd:Long):LiveData<List<JobItemFullInfo>>

    @Query("SELECT * FROM jobitemdbmodel ORDER BY dateInMils DESC LIMIT 1")
    suspend fun getLastJobItemWithCustomerDBModel():JobItemWithCustomerDBModel

    @Query("SELECT * FROM jobitemdbmodel WHERE id = :id")
    suspend fun getJobItem(id:Long): JobItemWithCustomerDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobItem(jobItem: JobItemDBModel):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editJobItem(jobItem: JobItemDBModel)

    @Query("DELETE FROM jobitemdbmodel WHERE id = :id")
    suspend fun deleteJobItem(id:Long)

}