package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.domain.job.JobItem
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer
import com.example.hairmasterplaner.getDayOfMonth
import com.example.hairmasterplaner.getMonth
import com.example.hairmasterplaner.getYear
import com.example.hairmasterplaner.ui.printToLog
import kotlinx.coroutines.launch

const val TV_DATE_START = true
const val TV_DATE_END = false

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobItemRepositoryImpl(application)

    private var _newJob = MutableLiveData<JobItemWithCustomer?>()
    val newJob: LiveData<JobItemWithCustomer?>
        get() = _newJob

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
        calendar.set(year, month, dayOfMonth)
        val dateInMils = calendar.timeInMillis
        var dateStart = _dateStart.value?:0
        var dateEnd = _dateEnd.value?:0
        when (currentTextView) {
            TV_DATE_START -> dateStart = dateInMils
            else -> dateEnd = dateInMils
        }
        if (!validateDateRange(dateStart,dateEnd)){
            dateStart = dateInMils
            dateEnd = dateInMils
        }
        _dateStart.value = dateStart
        _dateEnd.value = dateEnd
        _listOfJob.value = repository.getJobListInDateRange(dateStart,dateEnd).value
    }

    fun setCurrentTextView(isDateStart: Boolean) {
        currentTextView = isDateStart
    }

    private fun validateDateRange(dateStart: Long, dateEnd: Long): Boolean {
        return dateStart <= dateEnd
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