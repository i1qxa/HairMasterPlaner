package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.example.hairmasterplaner.domain.job.JobItemWithCustomer

const val TV_DATE_START = true
const val TV_DATE_END = false

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobItemRepositoryImpl(application)

    private var _dateStart = MutableLiveData<Date>()
    val dateStart:LiveData<Date>
    get() = _dateStart

    private var _dateEnd = MutableLiveData<Date>()
    val dateEnd:LiveData<Date>
    get() = _dateEnd

    private var currentTextView = TV_DATE_START

    private var _listOfJob = repository.getJobListInDateRange(0,999999999999999999)
    val listOfJob:LiveData<List<JobItemWithCustomer>>
    get() = _listOfJob

    init {
        val calendar = Calendar.getInstance()
        val currentDate = Date(
            calendar.get(java.util.Calendar.YEAR),
            calendar.get(java.util.Calendar.MONTH),
            calendar.get(java.util.Calendar.DAY_OF_MONTH),
        )
        setupDateStart(currentDate)
        setupDateEnd(currentDate)
    }

    fun changeDate(selectedDate: Date){
        when(currentTextView){
            TV_DATE_START -> setupDateStart(selectedDate)
            else -> setupDateEnd(selectedDate)
        }
    }

    fun setCurrentTextView(isDateStart:Boolean){
        currentTextView = isDateStart
    }

    //Если после изменения дата начала периода будет больше даты окончания,
    // то дате окончания присваивается значение даты начала периода и наоборот
    private fun setupDateStart(selectedDate:Date){
        val dateStart = selectedDate
        val dateEnd = dateEnd.value?:dateStart
        _dateStart.value = selectedDate
        if (!validateDateRange(dateStart,dateEnd)) setupDateEnd(dateStart)
    }

    private fun setupDateEnd(selectedDate: Date) {
        val dateEndValue = selectedDate
        val dateStart = dateStart.value ?: dateEndValue
        _dateEnd.value = dateEndValue
        if (!validateDateRange(dateStart, selectedDate)) setupDateStart(selectedDate)
    }

    private fun validateDateRange(dateStart:Date, dateEnd:Date):Boolean{
        return dateStart.dateInMils<=dateEnd.dateInMils
    }
}