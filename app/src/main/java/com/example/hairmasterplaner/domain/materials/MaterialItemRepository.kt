package com.example.hairmasterplaner.domain.materials

import androidx.lifecycle.LiveData

interface MaterialItemRepository {

    suspend fun getMaterialItem(materialId:Int):MaterialItem

    fun getListMaterialItems():LiveData<List<MaterialItem>>

    fun getListMaterialItemByIds(listIds:List<Int>):LiveData<List<MaterialItem>>

    suspend fun addMaterialItem(item:MaterialItem)

    suspend fun editMaterialItem(item:MaterialItem)

    suspend fun deleteMaterialItem(itemId:Int)

}