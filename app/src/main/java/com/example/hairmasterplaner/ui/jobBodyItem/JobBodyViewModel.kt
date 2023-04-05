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
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import com.example.hairmasterplaner.ui.toDateTime
import kotlinx.coroutines.launch
import java.util.*

const val NEW_ITEM_AMOUNT = 1
const val NEW_ITEM_PRICE = 2
const val JOB_BODY_ITEM_AMOUNT = 3
const val JOB_BODY_ITEM_PRICE = 4

class JobBodyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobBodyRepositoryImpl(application)

    private val jobRepository = JobItemRepositoryImpl(application)

    private var _jobBodyItemsList = MutableLiveData<List<JobBodyItem>>()
    val jobBodyItemsList: LiveData<List<JobBodyItem>>
        get() = _jobBodyItemsList

    private var _jobItemWithCustomerLD = MutableLiveData<JobItemWithCustomer>()
    val jobItemWithCustomerLD: LiveData<JobItemWithCustomer>
        get() = _jobItemWithCustomerLD

    private var _amountOfNewItem = MutableLiveData<Int?>()
    val amountOfNewItem:LiveData<Int?>
    get() = _amountOfNewItem

    private var _priceOfNewItem = MutableLiveData<Int?>()
    val priceOfNewItem:LiveData<Int?>
    get() = _priceOfNewItem

    private var _sumOfNewItem = MutableLiveData<Int>()
    val sumOfNewItem:LiveData<Int>
    get() = _sumOfNewItem

    private var _newJobElementItem = MutableLiveData<JobElementItem?>()
    val newJobElementItem:LiveData<JobElementItem?>
    get() = _newJobElementItem

    private var currentEditingTextView:Int? = null
    private val setOfTVCode = setOf(NEW_ITEM_AMOUNT, NEW_ITEM_PRICE, JOB_BODY_ITEM_AMOUNT, JOB_BODY_ITEM_PRICE)

    fun setupCurrentEditingTV(code:Int){
        if (setOfTVCode.contains(code)){
            currentEditingTextView = code
        }
        else throw RuntimeException("Unknown code of TextView")
    }

    fun setupNewJobElement(elementItem: JobElementItem){
        _newJobElementItem.value = elementItem
        _priceOfNewItem.value = elementItem.price?:0
    }

    fun updateNum(num:Int){
        when(currentEditingTextView){
            NEW_ITEM_AMOUNT -> _amountOfNewItem.value = num
            NEW_ITEM_PRICE -> _priceOfNewItem.value = num
            JOB_BODY_ITEM_AMOUNT -> TODO()
            JOB_BODY_ITEM_PRICE -> TODO()
        }
        currentEditingTextView = null
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


    fun addJobBodyItem() {
        if (_jobItemWithCustomerLD.value != null && _newJobElementItem.value!=null) {
            val newJobBodyItem = JobBodyItem(
                id = 0,
                jobId = _jobItemWithCustomerLD.value!!.jobItem.id,
                jobElementItemId = _newJobElementItem.value!!.id,
                amount = _amountOfNewItem.value ?: 1,
                price = _priceOfNewItem.value ?: 0
            )
            viewModelScope.launch {
                repository.addJobBodyItem(newJobBodyItem)
                clearNewJobElementData()
            }
        }
    }

    private fun clearNewJobElementData(){
        _newJobElementItem.value = null
        _priceOfNewItem.value = null
        _amountOfNewItem.value = null
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
