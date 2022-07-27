package com.example.ecommerceapp.data.repository.product

import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.domain.repository.ProductRepository

class FakeProductRepository : ProductRepository {
    private val products = mutableListOf<ProductListItem>()
    private val product = ProductListItem("","",0,"",1.0,"")

    init {
        products.add(ProductListItem("category1", "description1", 1, "image1", 1.0, "title1"))
        products.add(ProductListItem("category2", "description2", 2, "image2", 2.0, "title2"))
    }

    override suspend fun getProducts(): List<ProductListItem> {
        return products
    }

    override suspend fun getProductDetails(productId: String): ProductListItem {
        return product
    }
}