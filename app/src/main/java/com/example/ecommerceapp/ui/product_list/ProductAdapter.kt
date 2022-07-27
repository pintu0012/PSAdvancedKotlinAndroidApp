package com.example.ecommerceapp.ui.product_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.Glide
import com.example.ecommerceapp.data.model.ProductListItem
import com.example.ecommerceapp.databinding.FragmentProductBinding

import com.example.ecommerceapp.ui.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ProductAdapter(var productList:ArrayList<ProductListItem>,val productsRecyclerViewAction: ProductsRecyclerViewAction):RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable{

    var filteredProductList:ArrayList<ProductListItem> = ArrayList()

    fun updateProductList( newProductList:List<ProductListItem>){
        productList.clear()
        productList.addAll(newProductList)
        filteredProductList.clear()
        filteredProductList.addAll(newProductList)

        println(filteredProductList)
        println(productList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: FragmentProductBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(product:ProductListItem){
            itemBinding.productName.text = product.title
            itemBinding.productCategory.text = product.category
            itemBinding.price.text = "$ ${product.price}"
            Glide.with(itemBinding.productImage.context)
                .load(product.image)
                .into(itemBinding.productImage)

            itemBinding.cardView.setOnClickListener {
                println("ITEM CLICKED!....${product.id}")
                productsRecyclerViewAction.onClick(product.id.toString() )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredProductList[position])
    }

    override fun getItemCount(): Int {
        return filteredProductList.size
    }

    override fun getFilter(): Filter {
        return  object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: "".lowercase()
                if(charString.isEmpty()){
                    filteredProductList = productList
                }else{
                    val filteredList = ArrayList<ProductListItem>()
                    productList
                        .filter {
                            println(constraint)
                            (it.title.lowercase().contains(constraint!!))
//                            or
//                                    (it.author.contains(constraint)
//                                            )

                        }
                        .forEach { filteredList.add(it) }
                    filteredProductList = filteredList
                }
                return FilterResults().apply {
                    values= filteredProductList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               filteredProductList = if (results?.values == null)
                   ArrayList()
                else
                    results.values as ArrayList<ProductListItem>
                notifyDataSetChanged()
            }

        }
    }
}