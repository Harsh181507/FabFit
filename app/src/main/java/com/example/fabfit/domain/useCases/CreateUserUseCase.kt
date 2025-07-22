package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.UserData
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repo: Repo) {

    fun createUserUseCase(userData: UserData): Flow<ResultState<String>> {
        return repo.registerUserWithEmailAndPassword(userData)

    }
}