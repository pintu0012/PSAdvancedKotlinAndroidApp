package com.example.ecommerceapp.domain.usecase

import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class GetProductsUseCase constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<ProductListItem>>> = flow {

        try {
            emit(Resource.Loading())
            val products = productRepository.getProducts()
            if(products!=null && products.isNotEmpty()){
                emit(Resource.Success(products))
            }else{
                emit(Resource.Error("An Unexpected Error Occurred"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection "
                )
            )
        } catch (e: SocketTimeoutException) {
            emit(Resource.Error(e.localizedMessage ?: "Time out, please try again later"))
        }
    }
}