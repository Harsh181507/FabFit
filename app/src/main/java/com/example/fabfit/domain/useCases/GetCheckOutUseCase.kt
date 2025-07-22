package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.ProductDataModels
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCheckOutUseCase @Inject constructor(private val repo : Repo){
    fun getCheckOutUseCase(productId : String) : Flow<ResultState<ProductDataModels>> {
        return repo.getCheckout(productId)
    }
}