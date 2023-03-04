package com.example.hairmasterplaner.data.jobBody

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hairmasterplaner.data.jobBody.JobBodyItemDBModel

@Dao
interface JobBodyItemDBModelDao {
    @Query("SELECT * FROM jobbodyitemdbmodel WHERE jobId = :jobId")
    fun getJobBodyList(jobId:Long):LiveData<List<JobBodyItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobBodyItem(item: JobBodyItemDBModel)

    @Query("DELETE FROM jobbodyitemdbmodel WHERE jobId = :itemId")
    suspend fun deleteJobBodyItem(itemId:Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editJobBodyItem(item: JobBodyItemDBModel)
}