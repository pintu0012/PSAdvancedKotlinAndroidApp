package com.example.ecommerceapp.data.repository

import android.util.Log
import com.example.ecommerceapp.data.model.ProductList
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.source.cache.ProductCacheDataSource
import com.example.ecommerceapp.data.source.local.ProductLocalDataSource
import com.example.ecommerceapp.data.source.remote.ProductRemoteDataSource
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.repository.ProductRepository
import retrofit2.Response
import java.lang.Exception

class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource,
    private val productCacheDataSource: ProductCacheDataSource
) : ProductRepository {
    override suspend fun getProducts(): List<ProductListItem> {
        return getProductsFromCache()
    }

    override suspend fun getProductDetails(productId: String): ProductListItem {
        return getProductDetailsFromDB(productId)
    }

    fun responseToResource(response: Response<ProductList>): Resource<ProductList> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun getProductsFromApi(): List<ProductListItem> {
        println("getProductsFromApi CALLED!...")

        var productList: List<ProductListItem> = emptyList()
        try {
            val response = productRemoteDataSource.getProducts()
            val body = response.body()
            if (body != null) {
                productList = body
            }
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return productList
    }

    private suspend fun getProductsFromDB(): List<ProductListItem> {
        println("getProductsFromDB CALLED!...")

        lateinit var productList: List<ProductListItem>
        try {
            productList = productLocalDataSource.getProductsFromDB()
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if (productList.isNotEmpty()) {
            return productList
        } else {
            productList = getProductsFromApi()
            if (productList.isNotEmpty()) {
                productLocalDataSource.saveProductsToDB(productList)
            }
        }
        return productList
    }

    private suspend fun getProductsFromCache(): List<ProductListItem> {
        println("getProductsFromCache CALLED!...")
        lateinit var productList: List<ProductListItem>
        try {
            productList = productCacheDataSource.getProductsFromCache()
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if (productList.isNotEmpty()) {
            return productList
        } else {
            productList = getProductsFromDB()

            if (productList.isNotEmpty()) {
                productCacheDataSource.saveProductsToCache(productList)

            }
        }
        return productList
    }


//    private suspend fun getProductDetailsFromCache(productId:String): ProductListItem {
//        println("getProductDetailsFromCache CALLED!...")
//
//        lateinit var product: ProductListItem
//        try {
//            product = productCacheDataSource.getProductDetailsFromCache(productId)
//            Log.e("PRODUCT MODEL",product.toString())
//        } catch (e: Exception) {
//            Log.i("MyTag", e.message.toString())
//        }
//        if (product != null && product.title.isNotEmpty()) {
//            return product
//        } else {
//            product = getProductDetailsFromDB(productId)
//
//            if (product != null) {
//                productCacheDataSource.saveProductDetailsToCache(product)
//
//            }
//        }
//        return product
//    }

    private suspend fun getProductDetailsFromDB(productId:String): ProductListItem {
        println("getProductDetailsFromDB CALLED!...")
        lateinit var product: ProductListItem
        try {
            product = productLocalDataSource.getProductDetailsFromDB(productId)
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if (product != null) {
            return product
        } else {
            product = getProductDetailsFromApi(productId)

            if (product != null) {
                productLocalDataSource.saveProductDetailsToDB(product)

            }
        }
        return product
    }

    private suspend fun getProductDetailsFromApi(productId:String): ProductListItem {
        println("getProductDetailsFromApi CALLED!...")

        lateinit var product: ProductListItem
        try {
            val response = productRemoteDataSource.getProductDetails(productId)
            val body = response.body()
            if (body != null) {
                product = body
            }
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return product
    }


}