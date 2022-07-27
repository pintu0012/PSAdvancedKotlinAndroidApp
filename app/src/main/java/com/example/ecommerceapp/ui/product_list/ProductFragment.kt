package com.example.ecommerceapp.ui.product_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.data.util.Utils.showSnackBar
import com.example.ecommerceapp.databinding.FragmentProductListBinding
import com.example.ecommerceapp.ui.product_list.filter_bottom_sheet.FilterAction
import com.example.ecommerceapp.ui.product_list.filter_bottom_sheet.FilterBottomSheetDialogFragment
import com.example.ecommerceapp.ui.product_list.filter_bottom_sheet.SortBy


/**
 * A fragment representing a list of Items.
 */
class ProductFragment : Fragment() , ProductsRecyclerViewAction{

    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var binding: FragmentProductListBinding
    private val productAdapter = ProductAdapter(arrayListOf(),this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
//        setActionBarTitle()
        return binding.root
    }

    private fun setActionBarTitle() {
        (activity as MainActivity).title = "Product List"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchView()
        observeViewModel()
        displayProductList()
    }

    private fun initSearchView() {
        binding.searchView.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                productAdapter.filter.filter(s.toString())
            }
        })

        binding.clearSearchText.setOnClickListener {
            binding.searchView.text.clear()
        }

        binding.filterButton.setOnClickListener {
            showFilterBottomSheetDialog()
        }

    }

    private fun showFilterBottomSheetDialog() {
        val filterBottomSheetDialogFragment = FilterBottomSheetDialogFragment.newInstance()
        filterBottomSheetDialogFragment.show(
            childFragmentManager,
            FilterBottomSheetDialogFragment.TAG
        )

        val callback = object : FilterAction {
            override fun onClick(sortBy: SortBy) {
                println(sortBy)
                productListViewModel.sortProducts(sortBy)
            }
        }
        filterBottomSheetDialogFragment.setOnActionCompleteListener(callback)
    }

    private fun observeViewModel() {
        productListViewModel = (activity as MainActivity).productListViewModel
    }

    private fun initRecyclerView() {
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun displayProductList() {
        productListViewModel.getProducts()
        productListViewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ViewState.Loading -> {
                    println("LOADING STATE IS CALLED!")
                    binding.progressBar.visibility = VISIBLE
                    binding.searchLinearLayout.visibility = GONE
                }
                is ViewState.ProductsLoaded -> {
                    println("ProductsLoaded STATE IS CALLED!")
                    binding.progressBar.visibility = GONE
                    binding.searchLinearLayout.visibility = VISIBLE
                    productAdapter.updateProductList(state.products)
                    for (obj in state.products) {
                        println("VIEW STATE: ${obj.title} : ${obj.price}")
                    }

                }
                is ViewState.ProductsLoadFailure -> {
                    println("ProductsLoadFailure STATE IS CALLED!")
                    binding.progressBar.visibility = GONE
                    binding.searchLinearLayout.visibility = GONE
                    handleErrorMessage(state.errorMessage)

                }
            }
        })
    }

    private fun handleErrorMessage(errorMessage: String) {
        showSnackBar(binding.root, errorMessage)
    }

    override fun onClick(productId: String) {
        goToProductDetailsScreen(productId)
    }

    private fun goToProductDetailsScreen(productId: String) {
        val action = ProductFragmentDirections.actionGoToProductDetails(productId)
        Navigation.findNavController(binding.list).navigate(action)
//        findNavController().navigate(action)
    }



}
