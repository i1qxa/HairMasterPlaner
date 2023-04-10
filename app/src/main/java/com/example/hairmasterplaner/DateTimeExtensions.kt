package com.example.hairmasterplaner

import android.icu.util.Calendar

fun Long.toDateTime(showNameOfMonth:Boolean):String{
    val year = this.getYear()
    val month = this.getMonth()
    val dayOfMonth = this.getDayOfMonth()
    val hours = this.getHour()
    val minutes = this.getMinutes()
    val answer = StringBuilder()
    with(answer){
        append(addZeroIfNumSmallerTen(dayOfMonth))
        append(dayOfMonth)
        append(".")
        if (showNameOfMonth) append(month.convertToNameOfMonth()) else {
            append(addZeroIfNumSmallerTen(month))
            append(month)
        }
        append(".")
        append(year)
        append(" ")
        append(addZeroIfNumSmallerTen(hours))
        append(hours)
        append(":")
        append(addZeroIfNumSmallerTen(minutes))
        append(minutes)
    }
    return answer.toString()
}

private fun addZeroIfNumSmallerTen(num:Int):String{
    return if (num<10) "0" else ""
}

fun Long.toDate():String{
    val year = this.getYear()
    val month = this.getMonth()
    val dayOfMonth = this.getDayOfMonth()
    val answer = StringBuilder()
    with(answer){
        append(addZeroIfNumSmallerTen(dayOfMonth))
        append(dayOfMonth)
        append(".")
        append(addZeroIfNumSmallerTen(month))
        append(month)
        append(".")
        append(year)
    }
    return answer.toString()
}

fun Long.getYear():Int{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.YEAR)
}

fun Long.getMonth():Int{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MONTH) + 1
}

fun Long.getDayOfMonth():Int{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Long.getHour():Int{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Long.getMinutes():Int{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MINUTE)
}

fun Int.convertToNameOfMonth():String{
    return when(this){
        1 -> "Января"
        2 -> "Февраля"
        3 -> "Марта"
        4 -> "Апреля"
        5 -> "Мая"
        6 -> "Июня"
        7 -> "Июля"
        8 -> "Августа"
        9 -> "Сентября"
        10 -> "Октября"
        11 -> "Ноября"
        12 -> "Декабря"
        else -> "Число должно быть от 0 до 12"
    }
}