package com.example.ecommerceapp.ui.di.core

import com.example.ecommerceapp.data.db.ProductDao
import com.example.ecommerceapp.data.source.local.ProductLocalDataSource
import com.example.ecommerceapp.data.source.local.ProductLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideProductLocalDataSource(productDao: ProductDao):ProductLocalDataSource{
        return ProductLocalDataSourceImpl(productDao)
    }
}