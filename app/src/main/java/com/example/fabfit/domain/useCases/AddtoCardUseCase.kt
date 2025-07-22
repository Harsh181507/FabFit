package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.CartDataModels
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddtoCardUseCase @Inject constructor(private val repo: Repo) {

    fun addtoCard(cartDataModels: CartDataModels): Flow<ResultState<String>> {

        return repo.addToCart(cartDataModels)
    }

}