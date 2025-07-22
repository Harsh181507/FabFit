package com.example.fabfit.domain.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class ProductDataModels (
    val name: String ="",
    val description: String ="",
    val price: String ="",
    val finalPrice: String ="",
    val availableUnits : String = "",
    val category: String ="",
    val image: String ="",
    val date: Long = System.currentTimeMillis(),
    val createBy: String = "",
    var productId: String = "",

)