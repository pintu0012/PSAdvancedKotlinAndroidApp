package com.example.ecommerceapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.ui.product_details.ProductDetailsViewModel
//import com.example.ecommerceapp.ui.product_details.ProductDetailsViewModelFactory
import com.example.ecommerceapp.ui.product_list.ProductListViewModel
//import com.example.ecommerceapp.ui.product_list.ProductViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var productViewModelFactory: ProductViewModelFactory

//    @Inject
//    lateinit var productDetailsViewModelFactory: ProductDetailsViewModelFactory

    lateinit var productListViewModel: ProductListViewModel
    lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            setupBottomNavigationBar()
//        productListViewModel =
//            ViewModelProvider(this, productViewModelFactory)[ProductListViewModel::class.java]
//        productDetailsViewModel = ViewModelProvider(
//            this,
//            productDetailsViewModelFactory
//        )[ProductDetailsViewModel::class.java]
    }

    private fun setupBottomNavigationBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        // Set up Action Bar
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

//        // always show selected Bottom Navigation item as selected (return true)
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            println(item)
//            // In order to get the expected behavior, you have to call default Navigation method manually
//            NavigationUI.onNavDestinationSelected(item, navController)
//            return@setOnItemSelectedListener true
//        }

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(findNavController(R.id.fragmentContainerView))
//                || super.onOptionsItemSelected(item)
//    }


}