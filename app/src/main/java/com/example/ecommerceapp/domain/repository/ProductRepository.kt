package com.example.ecommerceapp.domain.repository

import com.example.ecommerceapp.data.model.ProductList
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): List<ProductListItem>?
    suspend fun getProductDetails(productId:String):ProductListItem
}