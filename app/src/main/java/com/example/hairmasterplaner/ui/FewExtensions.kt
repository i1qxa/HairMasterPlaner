package com.example.hairmasterplaner.ui

import android.app.Activity
import android.icu.util.Calendar
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

//Few extension functions
fun Long.toDateTime():String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val hours = calendar.get(Calendar.HOUR)
    val minutes = calendar.get(Calendar.MINUTE)
    val answer = StringBuilder()
    with(answer){
        append(dayOfMonth)
        append("/")
        append(month.convertToNameOfMonth())
        append("/")
        append(year)
        append(" ")
        append(hours)
        append(":")
        append(minutes)
    }
    return answer.toString()
}

fun Int.convertToNameOfMonth():String{
    return when(this){
        0 -> "Января"
        1 -> "Февраля"
        2 -> "Марта"
        3 -> "Апреля"
        4 -> "Мая"
        5 -> "Июня"
        6 -> "Июля"
        7 -> "Августа"
        8 -> "Сентября"
        9 -> "Октября"
        10 -> "Ноября"
        11 -> "Декабря"
        else -> "Число должно быть не больше 11"
    }
}
fun Any?.printToLog(tag:String){
    Log.d(tag, toString())
}
fun Any?.printToLog(){
    Log.d("DEBUG_INFO",toString())
}
fun Activity.toast(msg:String){
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}
fun Fragment.toast(msg: String){
    Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
}
fun View.gone() = run{visibility = View.GONE}
fun View.visible() = run{visibility = View.VISIBLE}
fun View.inVisible() = run{visibility = View.INVISIBLE}
infix fun View.goneIf(predicate:Boolean) = kotlin.run {
    visibility = if (predicate) View.GONE else View.VISIBLE
}
infix fun View.visibleIf(predicate:Boolean) = kotlin.run {
    visibility = if (predicate) View.VISIBLE else View.GONE
}
infix fun View.inVisibleIf(predicate:Boolean) = kotlin.run {
    visibility = if (predicate) View.INVISIBLE else View.VISIBLE
}