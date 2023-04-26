package com.example.hairmasterplaner.data.customer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hairmasterplaner.data.customer.CustomerItemDBModel
import com.example.hairmasterplaner.domain.customer.CustomerItem

@Dao
interface CustomerItemDBModelDao {

    @Query("SELECT * FROM customeritemdbmodel")
    fun getCustomerList():LiveData<List<CustomerItemDBModel>>

    @Query("SELECT * FROM customeritemdbmodel WHERE id = :id")
    suspend fun getCustomerItem(id: Int): CustomerItemDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomerItem(customerItem: CustomerItemDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListCustomer(customerList:List<CustomerItemDBModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editCustomerItem(customerItem: CustomerItemDBModel)

    @Query("DELETE FROM customeritemdbmodel WHERE id = :id")
    suspend fun deleteCustomerItem(id:Int)

}