package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.ProductDataModels
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecificCategoryItemsUseCase @Inject constructor(private val repo: Repo) {

    fun getSpecificCategoryItemsUseCase(categoryName: String): Flow<ResultState<List<ProductDataModels>>> {
        return repo.getSpecificCategoryItem(categoryName)
    }
}