package com.example.ecommerceapp.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.launchFragmentInHiltContainer
import com.example.ecommerceapp.ui.product_list.ProductFragment
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDaoTest {


    //Since We are testing architecture component and to execute them synchronously we
    //need to add instantTaskExecutorRule

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDao: ProductDao
    private lateinit var database: ProductDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductDatabase::class.java
        ).build()
        productDao = database.productDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveProductsTest() = runBlocking {
        val products = listOf<ProductListItem>(
            ProductListItem("category1", "description1", 1, "image1", 1.0, "title1"),
            ProductListItem("category2", "description2", 2, "image2", 2.0, "title2"),
            ProductListItem("category3", "description3", 3, "image3", 3.0, "title3"),
        )
        productDao.saveProducts(products)
        val allProducts = productDao.getProducts()
        Truth.assertThat(allProducts).isEqualTo(products)
    }

    @Test
    fun deleteProductsTest() = runBlocking {
        val products = listOf<ProductListItem>(
            ProductListItem("category1", "description1", 1, "image1", 1.0, "title1"),
            ProductListItem("category2", "description2", 2, "image2", 2.0, "title2"),
            ProductListItem("category3", "description3", 3, "image3", 3.0, "title3"),
        )
        productDao.saveProducts(products)
        productDao.deleteAllProducts()
        val allProducts = productDao.getProducts()
        Truth.assertThat(allProducts).isEmpty()
    }
}