package com.geektrust.interview.pratik_bhandari.di

import com.geektrust.interview.pratik_bhandari.data.repository.NewsRepository
import com.geektrust.interview.pratik_bhandari.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        yourRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}