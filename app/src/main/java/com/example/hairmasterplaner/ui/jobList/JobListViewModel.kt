package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.domain.job.DateRange
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import kotlinx.coroutines.launch

const val TV_DATE_START = true
const val TV_DATE_END = false

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobItemRepositoryImpl(application)

    private var _newJob = MutableLiveData<JobItemWithCustomer?>()
    val newJob: LiveData<JobItemWithCustomer?>
        get() = _newJob

    private var _dateRange = MutableLiveData(DateRange())
    val dateRange: LiveData<DateRange>
        get() = _dateRange

    private var currentTextView = TV_DATE_START

    private val _selectedJobItem = MutableLiveData<JobItemWithCustomer?>()
    val selectedJobItem: LiveData<JobItemWithCustomer?>
        get() = _selectedJobItem

    val listOfJob = _dateRange.switchMap { dateRange ->
        repository.getJobFullInfoListInDateRange(dateRange.dateStart, dateRange.dateEnd)
    }

    fun changeDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, dayOfMonth)
        val dateInMils = calendar.timeInMillis
        when (currentTextView) {
            TV_DATE_START -> _dateRange.value?.dateStart = dateInMils
            else -> _dateRange.value?.dateEnd = dateInMils
        }
        _dateRange.value = _dateRange.value
    }

    fun setCurrentTextView(isDateStart: Boolean) {
        currentTextView = isDateStart
    }

    fun addNewJobItem() {
        val date = Calendar.getInstance().timeInMillis
        val newJob = JobItem(
            0,
            date,
            null
        )
        viewModelScope.launch {
            val newJobId = repository.addJobItem(newJob)
            _newJob.postValue(repository.getJobItemWithCustomer(newJobId))
        }
    }

    fun getNavigationData(jobId: Long) {
        viewModelScope.launch {
            _selectedJobItem.postValue(repository.getJobItemWithCustomer(jobId))
        }
    }

    fun clearNavigationData(){
        _selectedJobItem.value = null
    }

    fun clearNewJob() {
        _newJob.value = null
    }
}