package com.example.hairmasterplaner.ui

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

//Few extension functions
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