package com.example.hairmasterplaner.domain.services

import com.example.hairmasterplaner.domain.materials.MaterialItem

data class ServiceItemWithMaterial(
    val id:Int,
    val name:String,
    val listMaterial:List<MaterialItem>,
    val price:Int,
)
