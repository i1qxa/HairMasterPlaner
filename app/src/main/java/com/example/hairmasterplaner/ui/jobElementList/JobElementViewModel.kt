package com.example.hairmasterplaner.ui.jobElementList

import android.app.Application
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
        }
    }

    fun getListService() {
        _listService.postValue(_listJobElement.value?.filter { it.isService })
    }

    fun getListMaterial() {
        _listMaterial.postValue(_listJobElement.value?.filter { !it.isService })
    }

    fun addJobElement(name: String, isService: Boolean, unitOM: String?) {
        if (name.isNotEmpty()) {
            val newJobElement = JobElementItem(0, name.trim(), isService, unitOM)
            viewModelScope.launch {
                repository.addJobElementItem(newJobElement)
            }
        }
    }

    fun deleteJobElement(id:Int){
        TODO("Нужно реализовать механизм удаления элемента с проверкой нахождения элемента " +
                "в других таблицах и зачисткой регистра цен")
    }

    fun editJobElement(id:Int, name:String, isService: Boolean, unitOM: String?){
        if (name.isNotEmpty()){
            val newVersion = JobElementItem(id,name.trim(),isService,unitOM)
            viewModelScope.launch {
                repository.editJobElementItem(newVersion)
            }
        }
    }


}