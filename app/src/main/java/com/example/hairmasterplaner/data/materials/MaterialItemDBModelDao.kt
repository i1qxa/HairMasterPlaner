package com.example.hairmasterplaner.data.materials

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hairmasterplaner.domain.materials.MaterialItem

@Dao
interface MaterialItemDBModelDao {

    @Query("SELECT * FROM materialitemdbmodel WHERE id = :materialId")
    suspend fun getMaterialItem(materialId:Int):MaterialItemDBModel

    @Query("SELECT * FROM materialitemdbmodel")
    fun getListMaterialItems():LiveData<List<MaterialItemDBModel>>

    @Query("SELECT * FROM materialitemdbmodel WHERE id IN (:listIds)")
    fun getListMaterialItemsByIds(listIds:List<Int>):LiveData<List<MaterialItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMaterialItem(item:MaterialItemDBModel)

    @Query("DELETE FROM materialitemdbmodel WHERE id = :materialId")
    suspend fun deleteMaterialItem(materialId:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editMaterialItem(item:MaterialItemDBModel)

}