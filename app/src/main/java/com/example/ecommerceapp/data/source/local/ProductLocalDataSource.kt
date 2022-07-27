package com.example.ecommerceapp.data.source.local

import com.example.ecommerceapp.data.model.ProductListItem

interface ProductLocalDataSource {
    suspend fun getProductsFromDB():List<ProductListItem>
    suspend fun getProductDetailsFromDB(product_id:String):ProductListItem
    suspend fun saveProductDetailsToDB(productDetails:ProductListItem)
    suspend fun saveProductsToDB(products:List<ProductListItem>)
    suspend fun clearAll()
}