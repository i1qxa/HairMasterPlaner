package com.example.hairmasterplaner.ui.jobBodyItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.customer.CustomerRepositoryImpl
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.data.jobBody.JobBodyRepositoryImpl
import com.example.hairmasterplaner.data.jobElement.JobElementRepositoryImpl
import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.domain.jobBody.JobBodyItem
import com.example.hairmasterplaner.domain.jobElement.JobElementItem
import com.example.hairmasterplaner.ui.printToLog
import com.example.hairmasterplaner.ui.toDateTime
import kotlinx.coroutines.launch
import java.util.*

class JobBodyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobBodyRepositoryImpl(application)

    private val jobRepository = JobItemRepositoryImpl(application)

    private val jobElementRepository = JobElementRepositoryImpl(application)

    private var jobId: Long? = null

    private var _customer = MutableLiveData<CustomerItem>()
    val customer: LiveData<CustomerItem>
        get() = _customer

    private var _jobBodyItemsList = MutableLiveData<List<JobBodyItem>>()
    val jobBodyItemsList: LiveData<List<JobBodyItem>>
        get() = _jobBodyItemsList

    private var _dateOfJob = MutableLiveData<String>()
    val dateOfJob:LiveData<String>
    get() = _dateOfJob

    private var _listService = jobElementRepository.getServiceList()
    val listService:LiveData<List<JobElementItem>>
    get() = _listService

    private var _listMaterials = jobElementRepository.getMaterialList()
    val listMaterials:LiveData<List<JobElementItem>>
    get() = _listMaterials

    fun loadJobBodyItemsList() {
        if (jobId != null) {
            _jobBodyItemsList.postValue(repository.getJobBodyList(jobId!!).value)
        }
    }

    fun addJobBodyItem(jobElementId: Int, amount: Int?, price: Int) {
        if (jobId != null) {
            val newJobBodyItem = JobBodyItem(
                id = 0,
                jobId = jobId!!,
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
        if (jobId != null) {
            val newItem = JobBodyItem(
                id = jobBodyItemId,
                jobId = jobId!!,
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
        if (jobId == null) {
            addJobItem(customerItem)
        } else {
            editJobItem(customerItem)
        }

    }

    private fun addJobItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            val date = Calendar.getInstance().timeInMillis
            val jobItem = JobItem(
                0,
                date,
                customerItem.id
            )
            jobRepository.addJobItem(jobItem)
            jobId = jobRepository.getLastJobItem().id
            updateCustomerAndDate(customerItem, date)
        }
    }

    private fun editJobItem(customerItem: CustomerItem) {
        viewModelScope.launch {
            val oldJobItem = jobRepository.getJobItemWithCustomer(jobId!!).jobItem
            val newJobItem = oldJobItem.copy(
                id = oldJobItem.id,
                dateInMils = oldJobItem.dateInMils,
                customerId = customerItem.id
            )
            jobRepository.editJobItem(newJobItem)
            updateCustomerAndDate(customerItem,oldJobItem.dateInMils)
        }
    }

    fun setupJobIdAndCustomer(jobItemWithCustomer: JobItemWithCustomer) {
        jobId = jobItemWithCustomer.jobItem.id
        updateCustomerAndDate(jobItemWithCustomer.customerItem, jobItemWithCustomer.jobItem.dateInMils)
    }

    private fun updateCustomerAndDate(customer:CustomerItem, date:Long){
        _customer.postValue(customer)
        _dateOfJob.postValue(date.toDateTime())
    }

}
