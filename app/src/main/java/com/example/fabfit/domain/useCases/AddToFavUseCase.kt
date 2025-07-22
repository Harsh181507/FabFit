package com.example.fabfit.domain.useCases

import com.example.fabfit.common.PRODUCT_COLLECTION
import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.CategoryDataModels
import com.example.fabfit.domain.models.ProductDataModels
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToFavUseCase @Inject constructor(private val repo: Repo) {

    fun addToFav(productDataModels: ProductDataModels): Flow<ResultState<String>> {
        return repo.addToFav(productDataModels)
    }
}