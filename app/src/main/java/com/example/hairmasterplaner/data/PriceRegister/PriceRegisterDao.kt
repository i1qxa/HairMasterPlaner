package com.example.hairmasterplaner.data.PriceRegister

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PriceRegisterDao {

    @Query(
        "SELECT * FROM PriceRegisterItemDBModel as reg " +
                "WHERE reg.date >= :dateStart AND reg.date <= :dateEnd"
    )
    fun getPriceRegisterListInDateRange(dateStart: String, dateEnd: Int)
            : LiveData<List<PriceRegisterItemDBModel>>

    @Query(
        "SELECT reg.price FROM PriceRegisterItemDBModel as reg " +
                "WHERE reg.date <= :date AND reg.jobElementId = :jobElementId ORDER BY reg.date LIMIT 1"
    )
    suspend fun getLastPriceAtDate(date: String, jobElementId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPriceRegisterRecord(record: PriceRegisterItemDBModel)

    @Query("DELETE FROM PriceRegisterItemDBModel WHERE id = :recordId")
    suspend fun deletePriceRegisterRecord(recordId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editPriceRegisterRecord(record: PriceRegisterItemDBModel)

}