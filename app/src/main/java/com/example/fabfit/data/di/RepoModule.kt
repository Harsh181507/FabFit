package com.example.fabfit.data.di

import com.example.fabfit.domain.repo.Repo
import com.example.fabfit.data.repo.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindRepo(
        repoImpl: RepoImpl
    ): Repo
}
