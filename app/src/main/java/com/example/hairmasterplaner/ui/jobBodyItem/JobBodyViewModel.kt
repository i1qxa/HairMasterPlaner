package com.example.hairmasterplaner.ui.jobBodyItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.data.jobBody.JobBodyRepositoryImpl
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem
import com.example.hairmasterplaner.ui.toDateTime
import kotlinx.coroutines.launch
import java.util.*

class JobBodyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobBodyRepositoryImpl(application)

    private val jobRepository = JobItemRepositoryImpl(application)

    private var _jobBodyItemsList = MutableLiveData<List<JobBodyItem>>()
    val jobBodyItemsList: LiveData<List<JobBodyItem>>
        get() = _jobBodyItemsList

    private var _jobItemWithCustomerLD = MutableLiveData<JobItemWithCustomer>()
    val jobItemWithCustomerLD: LiveData<JobItemWithCustomer>
        get() = _jobItemWithCustomerLD

    private var _amountOfNewItem = MutableLiveData<Int>()
    val amountOfNewItem:LiveData<Int>
    get() = _amountOfNewItem

    private var _priceOfNewItem = MutableLiveData<Int>()
    val priceOfNewItem:LiveData<Int>
    get() = _priceOfNewItem

    private var _sumOfNewItem = MutableLiveData<Int>()
    val sumOfNewItem:LiveData<Int>
    get() = _sumOfNewItem

    fun setupAmountOfNewItem(amount:Int){
        _amountOfNewItem.value = amount
        calculateSumOfNewItem()
    }

    fun setupPriceOfNewItem(price:Int){
        _priceOfNewItem.value = price
        calculateSumOfNewItem()
    }

    private fun calculateSumOfNewItem(){
        _sumOfNewItem.value = (_amountOfNewItem.value?:0) * (_priceOfNewItem.value?:0)
    }

    fun initJobItemWithCustomer(item: JobItemWithCustomer) {
        _jobItemWithCustomerLD.value = item
    }

    fun loadDataFromDB() {
        if (_jobItemWithCustomerLD.value != null) {
            _jobBodyItemsList.postValue(repository.getJobBodyList(_jobItemWithCustomerLD.value!!.jobItem.id).value)
        }
    }


    fun addJobBodyItem(jobElementId: Int, amount: Int?, price: Int) {
        if (_jobItemWithCustomerLD.value != null) {
            val newJobBodyItem = JobBodyItem(
                id = 0,
                jobId = _jobItemWithCustomerLD.value!!.jobItem.id,
                jobElementItemId = jobElementId,
                amount = amount ?: 1,
                price = price
            )
            viewModelScope.launch {
                repository.addJobBodyItem(newJobBodyItem)
            }
        }
    }

    fun editJobBodyItem(jobBodyItemId: Long, jobElementId: Int, amount: Int?, price: Int) {
        if (_jobItemWithCustomerLD.value != null) {
            val newItem = JobBodyItem(
                id = jobBodyItemId,
                jobId = _jobItemWithCustomerLD.value!!.jobItem.id,
                jobElementItemId = jobElementId,
                amount = amount ?: 1,
                price = price
            )
            viewModelScope.launch {
                repository.editJobBodyItem(newItem)
            }
        }
    }

    fun deleteJobBodyItem(jobBodyItemId: Long) {
        viewModelScope.launch {
            repository.deleteJobBodyItem(jobBodyItemId)
        }
    }

    fun addOrEditJobItem(customerItem: CustomerItem) {
        if (_jobItemWithCustomerLD.value == null) {
            addNewJobItem(customerItem)
        } else {
            editJobItem(customerItem)
        }
    }

    private fun addNewJobItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            val date = Calendar.getInstance().timeInMillis
            val jobItem = JobItem(
                0,
                date,
                customerItem.id
            )
            jobRepository.addJobItem(jobItem)
            val lastJobItem = jobRepository.getLastJobItem()
            updateJobItemWithCustomerLD(lastJobItem.id)
        }
    }

    private fun editJobItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            val oldJobItem = _jobItemWithCustomerLD.value!!.jobItem
            val newJobItem = oldJobItem.copy(
                id = oldJobItem.id,
                dateInMils = oldJobItem.dateInMils,
                customerId = customerItem.id
            )
            jobRepository.editJobItem(newJobItem)
            updateJobItemWithCustomerLD(newJobItem.id)
        }
    }

    private fun updateJobItemWithCustomerLD(jobId: Long) {
        viewModelScope.launch {
            _jobItemWithCustomerLD.postValue(jobRepository.getJobItemWithCustomer(jobId))
        }
    }

}
