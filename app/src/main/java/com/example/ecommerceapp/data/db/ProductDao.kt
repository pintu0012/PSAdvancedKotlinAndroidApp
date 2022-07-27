package com.example.ecommerceapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.data.model.ProductListItem

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(product: List<ProductListItem>)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM product_table")
    suspend fun getProducts():List<ProductListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: ProductListItem)

    @Query("SELECT * FROM product_table WHERE id = :id")
    suspend fun getProduct(id:String) : ProductListItem

}