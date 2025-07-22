package com.example.fabfit.domain.useCases

import android.net.Uri
import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileImageUseCase @Inject constructor(private val repo: Repo) {

    fun getUserProfileImageUseCase(uri: Uri): Flow<ResultState<String>> {

        return repo.userProfileImage(uri)
    }
}