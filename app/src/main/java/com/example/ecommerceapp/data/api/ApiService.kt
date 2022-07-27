package com.example.ecommerceapp.data.api

import com.example.ecommerceapp.data.model.ProductList
import com.example.ecommerceapp.data.model.ProductListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProductList(): Response<ProductList>

    @GET("products/{product_id}")
    suspend fun getProductDetails(@Path("product_id") product_id: String): Response<ProductListItem>
}