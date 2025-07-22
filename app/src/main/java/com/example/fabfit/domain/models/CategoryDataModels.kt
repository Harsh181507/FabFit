package com.example.fabfit.domain.models

data class CategoryDataModels (
    var name: String = "",
    var data : Long = System.currentTimeMillis(),
    var createBy: String="",
    var categoryImage : String=""


)