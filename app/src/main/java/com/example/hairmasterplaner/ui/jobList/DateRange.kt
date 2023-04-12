package com.example.hairmasterplaner.ui.jobList

import android.icu.util.Calendar
import com.example.hairmasterplaner.getDayOfMonth
import com.example.hairmasterplaner.getMonth
import com.example.hairmasterplaner.getYear

data class DateRange(
    var dateStart:Long,
    var dateEnd:Long,
) {
    fun updateDateStart(date:Long){
       dateStart = getBeginOfDay(date)
       if (dateStart>dateEnd) dateEnd = getEndOfDay(date)
    }
    fun updateDateEnd(date: Long){
        dateEnd = getEndOfDay(date)
        if (dateStart>dateEnd) dateStart = getBeginOfDay(date)
    }

    private fun getBeginOfDay(date: Long):Long{
        val calendar = Calendar.getInstance()
        calendar.set(
            date.getYear(),
            date.getMonth(),
            date.getDayOfMonth(),
            0,
            0,
            0
            )
        return calendar.timeInMillis
    }

    private fun getEndOfDay(date: Long):Long{
        val calendar = Calendar.getInstance()
        calendar.set(
            date.getYear(),
            date.getMonth(),
            date.getDayOfMonth(),
            23,
            59,
            59
        )
        return calendar.timeInMillis
    }
}