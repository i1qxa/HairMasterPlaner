package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.getDayOfMonth
import com.example.hairmasterplaner.getMonth
import com.example.hairmasterplaner.getYear
import kotlinx.coroutines.launch

const val TV_DATE_START = true
const val TV_DATE_END = false

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobItemRepositoryImpl(application)

    private var _newJob = MutableLiveData<JobItemWithCustomer?>()
    val newJob: LiveData<JobItemWithCustomer?>
        get() = _newJob

    private var _dateRange = MutableLiveData(DateRange(0,0))
    val dateRange:LiveData<DateRange>
    get() = _dateRange

    private var _dateStart = MutableLiveData<Long>()
    val dateStart: LiveData<Long>
        get() = _dateStart

    private var _dateEnd = MutableLiveData<Long>()
    val dateEnd: LiveData<Long>
        get() = _dateEnd

    private var currentTextView = TV_DATE_START

    //private var _listOfJob = repository.getJobListInDateRange(0, 999999999999999999)
    private var _listOfJob = MutableLiveData<List<JobItemWithCustomer>>()
    val listOfJob: LiveData<List<JobItemWithCustomer>>
        get() = _listOfJob

    init {
        val currentDate = Calendar.getInstance().timeInMillis
        changeDate(currentDate.getYear(), currentDate.getMonth() - 1, currentDate.getDayOfMonth())
    }

    fun changeDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month-1, dayOfMonth)
        val dateInMils = calendar.timeInMillis
        when (currentTextView) {
            TV_DATE_START -> _dateRange.value?.updateDateStart(dateInMils) //dateStart = dateInMils
            else -> _dateRange.value?.updateDateEnd(dateInMils) //dateEnd = dateInMils
        }
        _dateRange.value = _dateRange.value
        _listOfJob.value = repository.getJobListInDateRange(
            _dateRange.value?.dateStart?:0 ,
            _dateRange.value?.dateEnd?:999999999999999999)
            .value
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
            repository.addJobItem(newJob)
            _newJob.postValue(repository.getLastJobItemWithCustomer())
        }
    }

    fun clearNewJob() {
        _newJob.value = null
    }
}