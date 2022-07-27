package com.example.ecommerceapp.data.source.remote

import com.example.ecommerceapp.data.api.ApiService
import com.example.ecommerceapp.data.model.ProductList
import com.example.ecommerceapp.data.model.ProductListItem
import retrofit2.Response

class ProductRemoteDataSourceImpl(
    private val apiService: ApiService,
//    private val apiKey:String //IF API KEY IS REQUIRED
) : ProductRemoteDataSource {
    override suspend fun getProducts(): Response<ProductList> {
       return apiService.getProductList()
    }

    override suspend fun getProductDetails(productId: String): Response<ProductListItem> {
        return apiService.getProductDetails(productId)
    }
}