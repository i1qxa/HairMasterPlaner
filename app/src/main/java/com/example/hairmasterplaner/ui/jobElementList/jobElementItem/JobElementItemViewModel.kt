package com.example.hairmasterplaner.ui.jobElementList.jobElementItem

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.jobElement.JobElementRepositoryImpl
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import kotlinx.coroutines.launch

class JobElementItemViewModel(application: Application):AndroidViewModel(application) {
    private val repository = JobElementRepositoryImpl(application)

    fun addJobElement(name:String, isService:Boolean, unitOM:String, price:Int?){
        if (validateParams(name, isService, unitOM)){
            val newJobElementItem = if (isService){
                JobElementItem(0,name.trim(),isService,null,price)
            }else{
                JobElementItem(0,name.trim(),isService,unitOM.trim(),price)
            }
            viewModelScope.launch {
                repository.addJobElementItem(newJobElementItem)
            }
        }
    }

    fun editJobElement(id:Int, name:String, isService:Boolean, unitOM:String, price: Int){
        if (validateParams(name,isService,unitOM)){
            val newJobElementItem = if (isService){
                JobElementItem(id,name.trim(),isService,null, price)
            }else{
                JobElementItem(id,name.trim(),isService,unitOM.trim(), price)
            }
            viewModelScope.launch {
                repository.editJobElementItem(newJobElementItem)
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