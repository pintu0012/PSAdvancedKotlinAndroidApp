package com.example.ecommerceapp.data.source.cache

import com.example.ecommerceapp.data.model.ProductListItem

interface ProductCacheDataSource {
    suspend fun getProductsFromCache():List<ProductListItem>
    suspend fun getProductDetailsFromCache(productId:String):ProductListItem
    suspend fun saveProductsToCache(products:List<ProductListItem>)
    suspend fun saveProductDetailsToCache(product:ProductListItem)
}