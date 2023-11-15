package com.example.sigmaindustry.di

import com.example.sigmaindustry.data.repository.ServicesRepositoryImpl
import com.example.sigmaindustry.domain.repository.ServicesRepository
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
    abstract fun bindNewsRepository(newsRepositoryImpl: ServicesRepositoryImpl): ServicesRepository

}