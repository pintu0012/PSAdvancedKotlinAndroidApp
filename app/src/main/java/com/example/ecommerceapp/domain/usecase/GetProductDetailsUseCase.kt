package com.example.ecommerceapp.domain.usecase

import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    operator fun invoke(productId:String) = flow {
        try {
            emit(Resource.Loading())
            val product = productRepository.getProductDetails(productId)
            emit(Resource.Success(product))
        }catch (e: HttpException) {
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