package com.example.hairmasterplaner.domain.job

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobItem(
    val id : Int,
    val date : String,
    val customerId : Int,
):Parcelable{
    //Реализовать подсчет суммы заказа
}
