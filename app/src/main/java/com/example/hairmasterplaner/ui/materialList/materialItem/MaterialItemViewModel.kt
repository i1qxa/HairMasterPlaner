package com.example.hairmasterplaner.ui.materialList.materialItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hairmasterplaner.domain.materials.MaterialItem

class MaterialItemViewModel:ViewModel() {

    private var materialItemId = 0
    private val _errorEnterName = MutableLiveData<Boolean>()
    val errorEnterName:LiveData<Boolean>
        get() = _errorEnterName

    private val _response = MutableLiveData<MaterialItem>()
    val response:LiveData<MaterialItem>
        get() = _response


    fun prepareResponse(materialName:String, purchasePriceStr:String, rtlPriceStr:String){
        if (materialName.isEmpty()){
            _errorEnterName.value = true
        }
        else{
            val purchasePrice = purchasePriceStr.toIntOrNull()
            val rtlPrice = rtlPriceStr.toIntOrNull()
            val newMaterialItem = MaterialItem(
                id = materialItemId,
                name = materialName,
                purchasePrice = purchasePrice?:0,
                rtlPrice = rtlPrice?:0
            )
            _response.value = newMaterialItem
        }
        resetError()
    }

    private fun resetError(){
        _errorEnterName.value = false
    }

    fun setupMaterialItemId(id:Int){
        materialItemId = id
    }

}