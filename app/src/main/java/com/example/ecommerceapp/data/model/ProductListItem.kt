package com.example.ecommerceapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_table")
data class ProductListItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
//    @SerializedName("rating")
//    val rating: Rating?,
    @SerializedName("title")
    val title: String
)