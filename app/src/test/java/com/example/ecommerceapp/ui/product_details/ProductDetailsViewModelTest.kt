package com.example.ecommerceapp.ui.product_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.Observer
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.usecase.GetProductDetailsUseCase
import com.example.ecommerceapp.utils.TestContextProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductDetailsViewModel

    @Mock
    private lateinit var observer: Observer<ProductDetailsViewState>

    @Mock
    private lateinit var getProductDetailsUseCase: GetProductDetailsUseCase

    private val dispatcher = TestContextProvider()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ProductDetailsViewModel(getProductDetailsUseCase,dispatcher)
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun `Success State Works`() = dispatcher.test.runBlockingTest {
        val product = ProductListItem("category","description",0,"image",100.00,"title")
        `when`(getProductDetailsUseCase("1")).thenReturn(flowOf(Resource.Success(product)))
        viewModel.getProductDetails("1")
        Assert.assertEquals(ProductDetailsViewState.ProductLoaded(product),viewModel.viewState.value)
    }
}