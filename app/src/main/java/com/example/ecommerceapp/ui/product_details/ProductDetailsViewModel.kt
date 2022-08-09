package com.example.ecommerceapp.ui.product_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.CoroutineContextProvider
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.usecase.GetProductDetailsUseCase
import com.example.ecommerceapp.ui.base.BaseViewModel
import com.example.ecommerceapp.ui.product_list.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class ProductDetailsViewState() {
    object Loading : ProductDetailsViewState()
    data class ProductLoaded(val product: ProductListItem) : ProductDetailsViewState()
    data class ProductLoadFailure(val errorMessage: String) : ProductDetailsViewState()
}

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase:GetProductDetailsUseCase,
    contextProvider: CoroutineContextProvider
):BaseViewModel(contextProvider){

    val viewState = MutableLiveData<ProductDetailsViewState>()

    fun getProductDetails(productId:String){
        viewModelScope.launch(contextProvider.io) {
            getProductDetailsUseCase(productId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        viewState.postValue(
                            result.data?.let { ProductDetailsViewState.ProductLoaded(product= it) }
                        )
                    }
                    is Resource.Loading -> {
                        viewState.postValue(ProductDetailsViewState.Loading)
                    }
                    is Resource.Error -> {
                        viewState.postValue(ProductDetailsViewState.ProductLoadFailure("An Unexpected Error Has Occurred!"))
                    }
                }
            }
        }
    }

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")

}