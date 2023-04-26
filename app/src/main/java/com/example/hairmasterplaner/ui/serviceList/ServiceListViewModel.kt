package com.example.hairmasterplaner.ui.serviceList

import android.app.Application
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.services.ServiceRepositoryImpl
import kotlinx.coroutines.launch

class ServiceListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ServiceRepositoryImpl(application)

    private val _showServiceLD = MutableLiveData(true)
    val showService: LiveData<Boolean>
        get() = _showServiceLD

    val serviceList = repository.getServiceList()

    fun deleteServiceItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteServiceItem(itemId)
        }
    }

}
