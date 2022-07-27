package com.example.ecommerceapp.ui.di.core

import com.example.ecommerceapp.domain.repository.ProductRepository
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class UseCaseModule {

    @Provides
    fun provideGetProductsUseCase(productRepository: ProductRepository):GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }
}