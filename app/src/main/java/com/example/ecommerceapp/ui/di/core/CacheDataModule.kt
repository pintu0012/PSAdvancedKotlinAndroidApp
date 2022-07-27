package com.example.ecommerceapp.ui.di.core

import com.example.ecommerceapp.data.source.cache.ProductCacheDataSource
import com.example.ecommerceapp.data.source.cache.ProductCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class CacheDataModule {
    @Singleton
    @Provides
    fun provideProductCacheDataSource():ProductCacheDataSource{
        return ProductCacheDataSourceImpl()
    }
}