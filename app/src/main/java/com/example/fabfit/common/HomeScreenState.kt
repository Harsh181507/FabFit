package com.example.fabfit.common

import com.example.fabfit.domain.models.BannerDataModels
import com.example.fabfit.domain.models.CategoryDataModels
import com.example.fabfit.domain.models.ProductDataModels

data class HomeScreenState (
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val categories: List<CategoryDataModels>? = null,
    val products: List<ProductDataModels>? = null,
    val banners: List<BannerDataModels>? = null,

    )