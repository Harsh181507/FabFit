package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.ProductDataModels
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsInLimitsUseCase @Inject constructor(private val repo: Repo) {

    fun getProductsInLimitsUseCase(): Flow<ResultState<List<ProductDataModels>>> {
        return repo.getProductsInLimited()
    }
}