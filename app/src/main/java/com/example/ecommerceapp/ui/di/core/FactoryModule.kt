package com.example.ecommerceapp.ui.di.core

import com.example.ecommerceapp.domain.usecase.GetProductDetailsUseCase
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import com.example.ecommerceapp.ui.product_details.ProductDetailsViewModelFactory
import com.example.ecommerceapp.ui.product_list.ProductViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideProductViewModelFactory(
        getProductsUseCase: GetProductsUseCase,
    ):ProductViewModelFactory{
        return ProductViewModelFactory(getProductsUseCase)
    }

    @Provides
    @Singleton
    fun provideProductDetailViewModelFactory(
        getProductDetailsUseCase: GetProductDetailsUseCase
    ):ProductDetailsViewModelFactory{
        return ProductDetailsViewModelFactory(getProductDetailsUseCase)
    }
}