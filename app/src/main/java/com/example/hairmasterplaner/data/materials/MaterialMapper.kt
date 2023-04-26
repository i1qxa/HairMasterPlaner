package com.example.hairmasterplaner.data.materials

import com.example.hairmasterplaner.domain.materials.MaterialItem

class MaterialMapper {

    fun mapMaterialToDBModel(item:MaterialItem):MaterialItemDBModel{
        return MaterialItemDBModel(
            id = item.id,
            name = item.name,
            purchasePrice = item.purchasePrice,
            rtlPrice = item.rtlPrice,
        )
    }

    fun mapDBToMaterial(itemDB:MaterialItemDBModel):MaterialItem{
        return MaterialItem(
            id = itemDB.id,
            name = itemDB.name,
            purchasePrice = itemDB.purchasePrice,
            rtlPrice = itemDB.rtlPrice,
        )
    }

    fun mapDBListToMaterialList(list:List<MaterialItemDBModel>):List<MaterialItem>{
        return list.map { materialItemDBModel ->
            mapDBToMaterial(materialItemDBModel)
        }
    }
}