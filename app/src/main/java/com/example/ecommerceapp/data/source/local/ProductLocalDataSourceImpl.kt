package com.example.ecommerceapp.data.source.local

import com.example.ecommerceapp.data.db.ProductDao
import com.example.ecommerceapp.data.model.ProductListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductLocalDataSourceImpl(
    private val productDao: ProductDao
) : ProductLocalDataSource {
    override suspend fun getProductsFromDB(): List<ProductListItem> {
        return productDao.getProducts()
    }

    override suspend fun saveProductsToDB(products: List<ProductListItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.saveProducts(products)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.deleteAllProducts()
        }
    }

    override suspend fun getProductDetailsFromDB(product_id: String): ProductListItem {
        return productDao.getProduct(product_id)
    }

    override suspend fun saveProductDetailsToDB(productDetails: ProductListItem) {
        return productDao.saveProduct(productDetails)
    }
}