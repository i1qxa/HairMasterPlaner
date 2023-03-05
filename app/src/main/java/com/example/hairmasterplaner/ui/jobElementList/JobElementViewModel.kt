package com.example.hairmasterplaner.ui.jobElementList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.jobElement.JobElementRepositoryImpl
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import kotlinx.coroutines.launch

class JobElementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobElementRepositoryImpl(application)

    private var _listJobElement = MutableLiveData<List<JobElementItem>>()
    val listJobElement: LiveData<List<JobElementItem>>
        get() = _listJobElement

    private var _listService = MutableLiveData<List<JobElementItem>>()
    val listService: LiveData<List<JobElementItem>>
        get() = _listService

    private var _listMaterial = MutableLiveData<List<JobElementItem>>()
    val listMaterial: LiveData<List<JobElementItem>>
        get() = _listMaterial

    init {
        viewModelScope.launch {
            _listJobElement.postValue(repository.getJobElementList().value)
//            repository.getJobElementList().value?.forEach {
//                Log.i("List JobElement", it.toString())
//            }
        }
    }

    fun getListService() {
        _listService.postValue(_listJobElement.value?.filter { it.isService })
    }

    fun getListMaterial() {
        _listMaterial.postValue(_listJobElement.value?.filter { !it.isService })
    }


}