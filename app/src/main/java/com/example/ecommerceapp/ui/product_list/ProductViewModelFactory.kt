package com.example.ecommerceapp.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.Dispatchers

class ProductViewModelFactory (
    private val getProductsUseCase: GetProductsUseCase,
) :ViewModelProvider.Factory{

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return ProductListViewModel(getProductsUseCase) as T
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductListViewModel(getProductsUseCase,Dispatchers.IO) as T
    }

}