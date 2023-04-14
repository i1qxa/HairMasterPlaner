package com.example.hairmasterplaner.ui.jobBodyList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.data.jobBody.JobBodyRepositoryImpl
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem
import com.example.hairmasterplaner.domain.jobBody.JobBodyWithJobElement
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import kotlinx.coroutines.launch
import java.util.*

const val NEW_ITEM_AMOUNT = 1
const val NEW_ITEM_PRICE = 2
const val JOB_BODY_ITEM_AMOUNT = 3
const val JOB_BODY_ITEM_PRICE = 4

class JobBodyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobBodyRepositoryImpl(application)

    private val jobRepository = JobItemRepositoryImpl(application)

    private var _jobBodyItemsList = MutableLiveData<List<JobBodyWithJobElement>>()
    val jobBodyItemsList: LiveData<List<JobBodyWithJobElement>>
        get() = _jobBodyItemsList

    private var _jobBodyList = MutableLiveData<List<JobBodyWithJobElement>>()
    val jobBodyList:LiveData<List<JobBodyWithJobElement>>
    get() = _jobBodyList

//    val jobBodyList = Transformations.switchMap(_jobItemWithCustomerLD){ jobItem ->
//        repository.getJobBodyList(jobItem.jobItem.id)
//    }

    private var _jobItemWithCustomerLD = MutableLiveData<JobItemWithCustomer>()
    val jobItemWithCustomerLD: LiveData<JobItemWithCustomer>
        get() = _jobItemWithCustomerLD

    private var _amountOfNewItem = MutableLiveData<Int?>()
    val amountOfNewItem: LiveData<Int?>
        get() = _amountOfNewItem

    private var _priceOfNewItem = MutableLiveData<Int?>()
    val priceOfNewItem: LiveData<Int?>
        get() = _priceOfNewItem

    private var _newJobElementItem = MutableLiveData<JobElementItem?>()
    val newJobElementItem: LiveData<JobElementItem?>
        get() = _newJobElementItem

    private var currentEditingTextView: Int? = null
    private var currentEditingJobBodyItem: JobBodyItem? = null
    private val setOfTVCode =
        setOf(NEW_ITEM_AMOUNT, NEW_ITEM_PRICE, JOB_BODY_ITEM_AMOUNT, JOB_BODY_ITEM_PRICE)


    fun initJobItemWithCustomer(item: JobItemWithCustomer) {
        _jobItemWithCustomerLD.value = item
        _jobBodyList = repository.getJobBodyList(item.jobItem.id) as MutableLiveData<List<JobBodyWithJobElement>>
    }

    fun editJobItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            val oldJobItem = _jobItemWithCustomerLD.value!!.jobItem
            val newJobItem = oldJobItem.copy(
                customerId = customerItem.id
            )
            jobRepository.editJobItem(newJobItem)
            _jobItemWithCustomerLD.value = jobRepository.getJobItemWithCustomer(newJobItem.id)
        }
    }

    fun loadData() {
        val jobId = _jobItemWithCustomerLD.value?.jobItem?.id
        if(jobId!=null){
            _jobBodyItemsList =
                repository.getJobBodyWithJobElementList(jobId) as MutableLiveData<List<JobBodyWithJobElement>>
        }
    }

    fun setupCurrentEditingTV(code: Int) {
        if (setOfTVCode.contains(code)) {
            currentEditingTextView = code
        } else throw RuntimeException("Unknown code of TextView")
    }

    fun setupNewJobElement(elementItem: JobElementItem) {
        _newJobElementItem.value = elementItem
        _priceOfNewItem.value = elementItem.price ?: 0
    }

    fun updateNum(num: Int) {
        when (currentEditingTextView) {
            NEW_ITEM_AMOUNT -> _amountOfNewItem.value = num
            NEW_ITEM_PRICE -> _priceOfNewItem.value = num
            JOB_BODY_ITEM_AMOUNT -> updateItemAmount(num)
            JOB_BODY_ITEM_PRICE -> updateItemPrice(num)
        }
        currentEditingTextView = null
    }

    fun setupCurrentEditingJobBodyItem(item: JobBodyItem) {
        currentEditingJobBodyItem = item
    }

    private fun updateItemAmount(num: Int) {
        if (currentEditingJobBodyItem != null) {
            val newItem = currentEditingJobBodyItem?.copy(amount = num)
            newItem?.let { editJobBodyItem(it) }
        }
    }

    private fun updateItemPrice(num: Int) {
        if (currentEditingJobBodyItem != null) {
            val newItem = currentEditingJobBodyItem?.copy(price = num)
            newItem?.let { editJobBodyItem(it) }
        }
    }

    fun addJobBodyItem() {
        val jobId = _jobItemWithCustomerLD.value?.jobItem?.id
        val jobElementId = _newJobElementItem.value?.id
        val amount = _amountOfNewItem.value ?: 1
        val price = _priceOfNewItem.value ?: 0
        if (jobId != null && jobElementId != null) {
            val newJobBodyItem = JobBodyItem(
                id = 0,
                jobId = jobId,
                jobElementItemId = jobElementId,
                amount = amount,
                price = price
            )
            viewModelScope.launch {
                repository.addJobBodyItem(newJobBodyItem)
                clearNewJobElementData()
            }
        }
    }

    private fun clearNewJobElementData() {
        _newJobElementItem.value = null
        _priceOfNewItem.value = 0
        _amountOfNewItem.value = 0
    }

    fun editJobBodyItem(item: JobBodyItem) {
        viewModelScope.launch {
            repository.editJobBodyItem(item)
        }
    }

    fun deleteJobBodyItem(jobBodyItemId: Long) {
        viewModelScope.launch {
            repository.deleteJobBodyItem(jobBodyItemId)
        }
    }


}
