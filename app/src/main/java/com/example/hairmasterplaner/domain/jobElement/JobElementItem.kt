package com.example.hairmasterplaner.domain.jobElement

data class JobElementItem(
    val id : Int,
    val name : String,
    val isService : Boolean,
    val unitOM : String?,
)
