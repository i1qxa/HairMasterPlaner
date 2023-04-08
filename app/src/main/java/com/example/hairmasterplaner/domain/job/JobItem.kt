package com.example.hairmasterplaner.domain.job

import android.icu.util.Calendar
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobItem(
    val id : Long,
    val dateInMils : Long,
    val customerId : Int?,
):Parcelable{
    fun getFormattedDate():String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateInMils
        val builder = StringBuilder()
        with(builder){
            append(calendar.get(Calendar.DAY_OF_MONTH))
            append(".")
            append(calendar.get(Calendar.MONTH))
            append(".")
            append(calendar.get(Calendar.YEAR))
            append(":")
            append(calendar.get(Calendar.HOUR))
            append("/")
            append(calendar.get(Calendar.MINUTE))
        }
        return builder.toString()
    }
    //Реализовать подсчет суммы заказа
}
