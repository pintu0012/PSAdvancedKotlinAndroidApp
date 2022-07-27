package com.example.ecommerceapp.data.source.remote

import com.example.ecommerceapp.data.model.ProductList
import com.example.ecommerceapp.data.model.ProductListItem
import retrofit2.Response

interface ProductRemoteDataSource {
    suspend fun getProducts():Response<ProductList>
    suspend fun getProductDetails(productId:String):Response<ProductListItem>
}