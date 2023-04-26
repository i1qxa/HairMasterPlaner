package com.example.hairmasterplaner.domain.jobBody

import com.example.hairmasterplaner.domain.services.ServiceItem

data class JobBodyWithJobElement(
    val jobBodyItem:JobBodyItem,
    val serviceItem:ServiceItem,
)
