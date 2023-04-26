package com.example.hairmasterplaner.data.materials

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hairmasterplaner.data.AppDatabase
import com.example.hairmasterplaner.domain.materials.MaterialItem
import com.example.hairmasterplaner.domain.materials.MaterialItemRepository

class MaterialItemRepositoryImpl(application: Application):MaterialItemRepository {

    private val mapper = MaterialMapper()
    private val dao = AppDatabase.getInstance(application).materialItemDBModelDao()

    override suspend fun getMaterialItem(materialId: Int): MaterialItem {
        return mapper.mapDBToMaterial(dao.getMaterialItem(materialId))
    }

    override fun getListMaterialItems(): LiveData<List<MaterialItem>> {
        return dao.getListMaterialItems().map { listDB ->
            mapper.mapDBListToMaterialList(listDB)
        }
    }

    override suspend fun addMaterialItem(item: MaterialItem) {
        dao.addMaterialItem(mapper.mapMaterialToDBModel(item))
    }

    override suspend fun editMaterialItem(item: MaterialItem) {
        dao.editMaterialItem(mapper.mapMaterialToDBModel(item))
    }

    override suspend fun deleteMaterialItem(itemId: Int) {
        dao.deleteMaterialItem(itemId)
    }

    override fun getListMaterialItemByIds(listIds: List<Int>): LiveData<List<MaterialItem>> {
        return dao.getListMaterialItemsByIds(listIds).map {
            mapper.mapDBListToMaterialList(it)
        }
    }
}