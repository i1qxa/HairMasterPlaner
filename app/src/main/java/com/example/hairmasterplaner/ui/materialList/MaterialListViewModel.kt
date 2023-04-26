package com.example.hairmasterplaner.ui.materialList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.materials.MaterialItemRepositoryImpl
import com.example.hairmasterplaner.domain.materials.MaterialItem
import kotlinx.coroutines.launch

class MaterialListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MaterialItemRepositoryImpl(application)

    private val _materialItemList = repository.getListMaterialItems()
    val materialItemList: LiveData<List<MaterialItem>>
        get() = _materialItemList

    fun addOrEditMaterialItem(item: MaterialItem) {
        viewModelScope.launch {
            repository.addMaterialItem(item)
        }
    }
}