package com.example.ecommerceapp.ui.di.core

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.ecommerceapp.data.db.ProductDao
import com.example.ecommerceapp.data.db.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DataBaseModule {

    @Singleton
    @Provides
    fun provideProductDataBase(app: Application):ProductDatabase{
        return Room.databaseBuilder(app,ProductDatabase::class.java,"productDb").build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db:ProductDatabase):ProductDao{
        return  db.productDao()
    }

}