package com.example.ecommerceapp.ui.di.core

import com.example.ecommerceapp.data.api.ApiService
import com.example.ecommerceapp.data.source.remote.ProductRemoteDataSource
import com.example.ecommerceapp.data.source.remote.ProductRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class RemoteDataModule {
    @Singleton
    @Provides
    fun provideProductRemoteDataSource(apiService: ApiService):ProductRemoteDataSource{
        return ProductRemoteDataSourceImpl(apiService)
    }
}