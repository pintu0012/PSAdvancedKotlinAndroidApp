package com.example.ecommerceapp.ui.product_list.fakes

import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.ui.product_list.fakes.FakeValueFactory.randomDouble
import com.example.ecommerceapp.ui.product_list.fakes.FakeValueFactory.randomInt
import com.example.ecommerceapp.ui.product_list.fakes.FakeValueFactory.randomString

object FakeUiData {

    fun getProducts(
        size:Int,
        isRandomId:Boolean =true,
    ):List<ProductListItem>{
        val products = mutableListOf<ProductListItem>()
        repeat(size){
            products.add(createProducts(isRandomId))
        }
        return products
    }

    private fun createProducts(isRandomId: Boolean): ProductListItem {
        return ProductListItem(
            id = if (isRandomId) randomInt() else 1,
            title = randomString(),
            category = randomString(),
            description = randomString(),
            image = randomString(),
            price = randomDouble()
        )
    }
}