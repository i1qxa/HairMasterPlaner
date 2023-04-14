package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.*
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.domain.job.DateRange
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

    private var _dateRange = MutableLiveData(DateRange())
    val dateRange:LiveData<DateRange>
    get() = _dateRange

    private var currentTextView = TV_DATE_START

    val listOfJob = Transformations.switchMap(_dateRange){ dateRange ->
        repository.getJobListInDateRange(dateRange.dateStart, dateRange.dateEnd)
    }

    fun changeDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month-1, dayOfMonth)
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
            repository.addJobItem(newJob)
            _newJob.postValue(repository.getLastJobItemWithCustomer())
        }
    }

    fun clearNewJob() {
        _newJob.value = null
    }
}