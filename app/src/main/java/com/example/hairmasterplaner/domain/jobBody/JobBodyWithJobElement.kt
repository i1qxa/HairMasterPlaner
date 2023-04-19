package com.example.hairmasterplaner.domain.jobBody

import com.example.hairmasterplaner.domain.jobElement.JobElementItem

data class JobBodyWithJobElement(
    val jobBodyItem:JobBodyItem,
    val jobElementItem:JobElementItem,
)
