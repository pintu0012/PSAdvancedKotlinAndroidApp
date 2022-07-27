package com.example.ecommerceapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecommerceapp.data.model.ProductListItem
import javax.inject.Inject

@Database(entities = [ProductListItem::class], version = 1, exportSchema = false)
abstract class ProductDatabase  : RoomDatabase()  {
    abstract fun productDao(): ProductDao
}