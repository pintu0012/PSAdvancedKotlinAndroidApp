package com.example.ecommerceapp.ui.product_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ecommerceapp.data.util.Resource
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import com.example.ecommerceapp.ui.product_list.fakes.FakeUiData
import com.example.ecommerceapp.utils.TestContextProvider
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductListViewModel

    @Mock
    private lateinit var observer: Observer<ViewState>

    @Mock
    private lateinit var getProductsUseCase: GetProductsUseCase

    private val dispatcher = TestContextProvider()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ProductListViewModel(getProductsUseCase, dispatcher)
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun `Success State Works`() = dispatcher.test.runBlockingTest {
        val products = FakeUiData.getProducts(0)
        `when`(getProductsUseCase()).thenReturn(flowOf(Resource.Success(products)))
        viewModel.getProducts()
        Assert.assertEquals(ViewState.ProductsLoaded(products), viewModel.viewState.value)
    }

    @Test
    fun `Failure State works`() = dispatcher.test.runBlockingTest {
        `when`(getProductsUseCase()).thenReturn(flowOf(Resource.Error("An Unexpected Error Has Occurred!")))
        viewModel.getProducts()
        Assert.assertEquals(
            ViewState.ProductsLoadFailure("An Unexpected Error Has Occurred!"),
            viewModel.viewState.value
        )
    }

    @Test
    fun `Loading State Works`() = dispatcher.test.runBlockingTest {
        `when`(getProductsUseCase()).thenReturn(flowOf(Resource.Loading()))
        viewModel.getProducts()
        Assert.assertEquals(ViewState.Loading, viewModel.viewState.value)
    }


}