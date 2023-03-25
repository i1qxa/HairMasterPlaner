package com.example.hairmasterplaner.ui

import android.app.Activity
import android.icu.util.Calendar
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.timepicker.TimeFormat
import java.sql.Time
import java.util.*

//Few extension functions
fun Long.toDateTime():String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    TODO("Разобраться с конвертацией даты времени")
    return calendar
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