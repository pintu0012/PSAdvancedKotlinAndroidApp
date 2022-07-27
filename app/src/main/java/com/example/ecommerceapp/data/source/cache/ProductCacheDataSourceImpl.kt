package com.example.ecommerceapp.data.source.cache

import com.example.ecommerceapp.data.model.ProductListItem

class ProductCacheDataSourceImpl : ProductCacheDataSource {
    private var productList = ArrayList<ProductListItem>()
    private var product = ProductListItem("","",0,"",0.0,"")
    override suspend fun getProductsFromCache(): List<ProductListItem> {
        return productList
    }

    override suspend fun saveProductsToCache(products: List<ProductListItem>) {
        productList.clear()
        productList = ArrayList(products)
    }

    override suspend fun getProductDetailsFromCache(productId: String): ProductListItem {
        return product
    }

    override suspend fun saveProductDetailsToCache(newproduct: ProductListItem) {
        product = newproduct
    }
}