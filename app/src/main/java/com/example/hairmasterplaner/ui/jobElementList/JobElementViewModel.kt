package com.example.hairmasterplaner.ui.jobElementList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.jobElement.JobElementRepositoryImpl
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import kotlinx.coroutines.launch

class JobElementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobElementRepositoryImpl(application)

    private var _listJobElement = repository.getJobElementList()
    val listJobElement : LiveData<List<JobElementItem>>
    get() = _listJobElement


    private var _listService = repository.getServiceList()
    val listService : LiveData<List<JobElementItem>>
    get() = _listService

    private var _listMaterial = repository.getMaterialList()
    val listMaterial : LiveData<List<JobElementItem>>
    get() = _listMaterial
    
    fun deleteJobElementItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteJobElementItem(itemId)
        }
    }

}