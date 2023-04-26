package com.example.hairmasterplaner.ui.serviceList.serviceItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.services.ServiceRepositoryImpl
import com.example.hairmasterplaner.domain.services.ServiceItem
import kotlinx.coroutines.launch

class ServiceItemViewModel(application: Application):AndroidViewModel(application) {
    private val repository = ServiceRepositoryImpl(application)

    fun addJobElement(name:String, isService:Boolean, unitOM:String, price:Int?){
        if (validateParams(name, isService, unitOM)){
            val newServiceItem = if (isService){
                ServiceItem(0,name.trim(),isService,null,price)
            }else{
                ServiceItem(0,name.trim(),isService,unitOM.trim(),price)
            }
            viewModelScope.launch {
                repository.addServiceItem(newServiceItem)
            }
        }
    }

    fun editJobElement(id:Int, name:String, isService:Boolean, unitOM:String, price: Int){
        if (validateParams(name,isService,unitOM)){
            val newServiceItem = if (isService){
                ServiceItem(id,name.trim(),isService,null, price)
            }else{
                ServiceItem(id,name.trim(),isService,unitOM.trim(), price)
            }
            viewModelScope.launch {
                repository.editServiceItem(newServiceItem)
            }
        }
    }

    private fun validateParams(name:String, isService:Boolean, unitOM:String):Boolean{
        return if (isService){
            name.isNotEmpty()
        }
        else{
            name.isNotEmpty() && unitOM.isNotEmpty()
        }
    }
}