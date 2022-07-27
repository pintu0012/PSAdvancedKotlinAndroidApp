package com.example.ecommerceapp.ui.product_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import com.example.ecommerceapp.ui.product_list.filter_bottom_sheet.SortBy
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import kotlin.Comparator

sealed class ViewState() {
    object Loading : ViewState()
    data class ProductsLoaded(val products: List<ProductListItem>) : ViewState()
    data class ProductsLoadFailure(val errorMessage: String) : ViewState()
}

class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    ) : ViewModel() {

//    val productList: MutableLiveData<List<ProductListItem>> = MutableLiveData()
    val viewState = MutableLiveData<ViewState>()

    fun getProducts() {
        viewModelScope.launch(defaultDispatcher) {
            getProductsUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        viewState.postValue(
                            ViewState.ProductsLoaded(products = result.data ?: emptyList())
                        )
                    }
                    is Resource.Loading -> {
                        viewState.postValue(ViewState.Loading)
                    }

                    is Resource.Error -> {
                        viewState.postValue(ViewState.ProductsLoadFailure("An Unexpected Error Has Occurred!"))
                    }
                }
            }

        }
    }

    fun sortProducts(sortBy: SortBy) {
        viewModelScope.launch(defaultDispatcher) {
            getProductsUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if(result.data!=null && result.data.isNotEmpty()){
                            var sortedList = result.data
                            sortedList = if(sortBy == SortBy.HighToLow){
                                result.data.sortedByDescending { product -> product.price }
                            }else if (sortBy == SortBy.LowToHigh){
                                result.data.sortedBy { product -> product.price }
                            } else {
                                result.data
                            }
                            for (obj in result.data) {
                                println(obj.price)
                            }
                            viewState.postValue(
                                ViewState.ProductsLoaded(products = sortedList ?: emptyList())
                            )
                        }else{
                            viewState.postValue(
                                ViewState.ProductsLoaded(products =  emptyList())
                            )
                        }

                    }
                    is Resource.Loading -> {
                        viewState.postValue(ViewState.Loading)
                    }

                    is Resource.Error -> {
                        viewState.postValue(ViewState.ProductsLoadFailure("An Unexpected Error Has Occurred!"))
                    }
                }
            }

        }
    }

//            emit(productList)

    /** IF UPDATE PRODUCT IS IMPLEMENTED
    fun updateProducts() = liveData{
    val productList = updateProductsUseCase()
    emit(productList)
    }**/
}