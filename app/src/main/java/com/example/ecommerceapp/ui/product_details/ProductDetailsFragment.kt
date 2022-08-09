package com.example.ecommerceapp.ui.product_details

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.data.util.Utils.showSnackBar
import com.example.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.example.ecommerceapp.ui.base.BaseFragment
import com.example.ecommerceapp.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding, BaseViewModel>() {

    override val viewModel: ProductDetailsViewModel by viewModels()

    override fun getViewBinding(): FragmentProductDetailsBinding  = FragmentProductDetailsBinding.inflate(layoutInflater)
    // TODO: Rename and change types of parameters
    private var productId: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = ProductDetailsFragmentArgs.fromBundle(it).productId
        }
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setActionBarTitle() {
        (activity as MainActivity).title = "Details"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViewModel()
        displayProductDetails()
    }

//    private fun initViewModel() {
//        viewModel = (activity as MainActivity).productDetailsViewModel
//    }

    private fun displayProductDetails() {
        if (productId.isNotEmpty()) {
            viewModel.getProductDetails(productId)
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ProductDetailsViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ProductDetailsViewState.ProductLoaded -> {
                    binding.progressBar.visibility = View.GONE
                    showProductDetails(state.product)

                }
                is ProductDetailsViewState.ProductLoadFailure -> {
                    binding.progressBar.visibility = View.GONE
                    handleErrorMessage(state.errorMessage)
                }
            }
        })
    }

    private fun showProductDetails(product: ProductListItem) {
        binding.linearLayoutCompat.visibility = VISIBLE
        binding.title.text = product.title
        binding.category.text = product.category
        binding.description.text = product.description
        binding.price.text = "Rs. ${product.price}"
        Glide.with(binding.imageView.context)
            .load(product.image)
            .into(binding.imageView)
    }

    private fun handleErrorMessage(errorMessage: String) {
        Timber.e(errorMessage)
        showSnackBar(binding.root, errorMessage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                parentFragmentManager.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }



}