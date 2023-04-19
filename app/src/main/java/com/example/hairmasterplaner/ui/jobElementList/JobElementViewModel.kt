package com.example.hairmasterplaner.ui.jobElementList

import android.app.Application
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.jobElement.JobElementRepositoryImpl
import kotlinx.coroutines.launch

class JobElementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobElementRepositoryImpl(application)

    private val _showServiceLD = MutableLiveData(true)
    val showService: LiveData<Boolean>
        get() = _showServiceLD

    val jobElementList = _showServiceLD.switchMap { isService ->
        repository.getJobElementList(isService)
    }

    fun showService() {
        _showServiceLD.value = true
    }

    fun showMaterial() {
        _showServiceLD.value = false
    }

    fun deleteJobElementItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteJobElementItem(itemId)
        }
    }

}
