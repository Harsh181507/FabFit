package com.example.fabfit.domain.useCases

import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.UserData
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repo: Repo){
    fun loginUserUseCase(userData: UserData) : Flow<ResultState<String>>{
        return repo.loginUserWithEmailAndPassword(userData)
    }
}