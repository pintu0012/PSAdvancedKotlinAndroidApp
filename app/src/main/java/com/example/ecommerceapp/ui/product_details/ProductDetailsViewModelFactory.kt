package com.example.ecommerceapp.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapp.domain.usecase.GetProductDetailsUseCase
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import javax.inject.Inject

class ProductDetailsViewModelFactory (
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(getProductDetailsUseCase) as T

    }

}