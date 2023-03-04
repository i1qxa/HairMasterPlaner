package com.example.hairmasterplaner.domain.PriceRegister

data class PriceRegisterItem(
    val id : Int,
    val jobElementId : Int,
    val price : Int,
    val date : String,
)
