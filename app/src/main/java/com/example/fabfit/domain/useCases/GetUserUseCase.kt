package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.UserDataParent
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: Repo) {

    fun getUserUseCase(uid:String): Flow<ResultState<UserDataParent>> {
        return repo.getUserById(uid)
    }
}