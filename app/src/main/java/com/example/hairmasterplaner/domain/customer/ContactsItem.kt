package com.example.hairmasterplaner.domain.customer

data class ContactsItem(
    var shouldAdd:Boolean = false,
    val name:String?,
    val phone:String?,
)