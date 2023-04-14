package com.example.hairmasterplaner.ui.jobElementList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.jobElement.JobElementRepositoryImpl
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import kotlinx.coroutines.launch
import java.time.LocalDate

class JobElementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobElementRepositoryImpl(application)

    private val _showServiceLD = MutableLiveData(true)
    val showService:LiveData<Boolean>
    get() = _showServiceLD

    private var _listService = repository.getServiceList()
    val listService: LiveData<List<JobElementItem>>
        get() = _listService

    private var _listMaterial = repository.getMaterialList()
    val listMaterial: LiveData<List<JobElementItem>>
        get() = _listMaterial

    val jobElementList = Transformations.switchMap(_showServiceLD){ isServices ->
        when(isServices){
            true -> repository.getServiceList()
            false -> repository.getMaterialList()
        }
    }

    fun showService(){
        _showServiceLD.value = true
    }

    fun showMaterial(){
        _showServiceLD.value = false
    }

    fun deleteJobElementItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteJobElementItem(itemId)
        }
    }




}
