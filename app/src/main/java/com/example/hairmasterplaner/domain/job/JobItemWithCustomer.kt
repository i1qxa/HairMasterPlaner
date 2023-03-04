package com.example.hairmasterplaner.domain.job

import com.example.hairmasterplaner.domain.customer.CustomerItem
import com.example.hairmasterplaner.domain.job.JobItem

data class JobItemWithCustomer(
    val jobItem: JobItem,
    val customerItem: CustomerItem,
)
