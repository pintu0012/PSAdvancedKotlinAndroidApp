package com.example.ecommerceapp.ui.di.core

import com.example.ecommerceapp.data.repository.ProductRepositoryImpl
import com.example.ecommerceapp.data.source.cache.ProductCacheDataSource
import com.example.ecommerceapp.data.source.local.ProductLocalDataSource
import com.example.ecommerceapp.data.source.remote.ProductRemoteDataSource
import com.example.ecommerceapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        productRemoteDataSource: ProductRemoteDataSource,
        productLocalDataSource: ProductLocalDataSource,
        productCacheDataSource: ProductCacheDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(
            productRemoteDataSource,
            productLocalDataSource,
            productCacheDataSource
        )
    }
}