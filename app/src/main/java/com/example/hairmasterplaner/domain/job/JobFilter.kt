package com.example.hairmasterplaner.domain.job

import com.example.hairmasterplaner.domain.customer.CustomerItem

class JobFilter(
    var dateRange: DateRange = DateRange(),
    var customer:CustomerItem?,
)