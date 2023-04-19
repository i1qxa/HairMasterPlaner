package com.example.hairmasterplaner.domain.job

import android.icu.util.Calendar
import com.example.hairmasterplaner.getDayOfMonth
import com.example.hairmasterplaner.getMonth
import com.example.hairmasterplaner.getYear

//Дата начала периода всегда должна быть меньше даты окончания
//Дате начала периода по умолчанию присваивается время начала дня,
// а дате окончания периода время конца дня
class DateRange {
    var dateStart:Long = getCurrentTime()
        set(value) {
            field = getBeginOfDay(value)
            if (field>dateEnd) dateEnd = value
        }

    var dateEnd:Long = getCurrentTime()
        set(value) {
            field = getEndOfDay(value)
            if (field<dateStart) dateStart = value
        }

//    private fun getBeginOfDay(date: Long):Long{
//        val calendar = Calendar.getInstance()
//        calendar.set(
//            date.getYear(),
//            date.getMonth(),
//            date.getDayOfMonth(),
//            0,
//            0,
//            0
//            )
//        return calendar.timeInMillis
//    }

//    private fun getEndOfDay(date: Long):Long{
//        val calendar = Calendar.getInstance()
//        calendar.set(
//            date.getYear(),
//            date.getMonth(),
//            date.getDayOfMonth(),
//            23,
//            59,
//            59
//        )
//        return calendar.timeInMillis
//    }

    override fun equals(other: Any?): Boolean =
        other is DateRange && other.dateStart == dateStart && other.dateEnd == dateEnd

    override fun toString(): String {
        return "DateStart : $dateStart / DateEnd : $dateEnd"
    }

    override fun hashCode(): Int {
        var result = dateStart.hashCode()
        result = 31 * result + dateEnd.hashCode()
        return result
    }
    companion object{
        private val calendar = Calendar.getInstance()
        private fun getCurrentTime() = calendar.timeInMillis
        private fun getBeginOfDay(date: Long):Long{
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
}