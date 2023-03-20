package com.example.hairmasterplaner.ui.jobList

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hairmasterplaner.data.job.JobItemRepositoryImpl
import com.google.android.material.timepicker.TimeFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobItemRepositoryImpl(application)

    private val calendar = Calendar.getInstance().time

    private var _dateStart = MutableLiveData<String>()
    val dateStart:LiveData<String>
    get() = _dateStart

    private var _dateEnd = MutableLiveData<String>()
    val dateEnd:LiveData<String>
    get() = _dateEnd

    val sdf = SimpleDateFormat("yyyy/MM/dd")
    val currentDate = sdf.format(calendar)

    init {

        _dateStart.value = currentDate.toString()
        _dateEnd.value = currentDate.toString()
    }

}