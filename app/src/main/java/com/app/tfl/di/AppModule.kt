package com.app.tfl.di

import com.app.tfl.Repository
import com.app.tfl.RepositoryImpl
import com.app.tfl.api.RetrofitInstance
import com.app.tfl.api.StatusEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesAPI() = RetrofitInstance.API

    @Provides
    @Singleton
    fun providesRepository(api: StatusEndPoints) :Repository= RepositoryImpl(api)
}