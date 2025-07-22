package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.CategoryDataModels
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryInLimitUseCase @Inject constructor(private val repo: com.example.fabfit.domain.repo.Repo) {

    fun getCategoryInLimitUseCase(): Flow<ResultState<List<CategoryDataModels>>> {
        return repo.getCategoriesInLimited()
    }
}