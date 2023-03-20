package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import java.text.SimpleDateFormat

const val DATE_START = true
const val DATE_END = false

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobItemRepositoryImpl(application)

    val calendarStart = Calendar.getInstance()

    val calendarEnd = Calendar.getInstance()

    private var _dateStart = MutableLiveData<String>()
    val dateStart:LiveData<String>
    get() = _dateStart

    private var _dateEnd = MutableLiveData<String>()
    val dateEnd:LiveData<String>
    get() = _dateEnd

    private val currentPeriod = MutableLiveData<Int>()

    private var currentTextView = DATE_START

    val sdf = SimpleDateFormat("yyyy/MM/dd")


    fun setupCurrentPeriod(periodPosition:Int){
        currentPeriod.value = periodPosition
    }

    fun changeDate(year:Int, month:Int, day:Int){
        when(currentTextView){
            DATE_START -> setupDateStart(year,month,day)
            else -> setupDateEnd(year,month,day)
        }
    }

    fun setCurrentTextView(isDateStart:Boolean){
        currentTextView = isDateStart
    }

    private fun setupDateStart(year:Int, month:Int, day:Int){
        calendarStart.set(year,month,day)
        _dateStart.value = sdf.format(calendarStart.timeInMillis)
        if (calendarStart.timeInMillis>calendarEnd.timeInMillis) setupDateEnd(year,month,day)
    }

    private fun setupDateEnd(year:Int, month:Int, day:Int){
        calendarEnd.set(year,month,day)
        _dateEnd.value = sdf.format(calendarEnd.timeInMillis)
        if (calendarStart.timeInMillis>calendarEnd.timeInMillis) setupDateStart(year,month,day)
    }

}