package com.example.ecommerceapp.ui.product_list.filter_bottom_sheet

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceapp.databinding.FragmentFilterBottomSheetDialogListDialogBinding
import java.util.logging.Filter


enum class SortBy{
    HighToLow, LowToHigh, NoFilter
}

class FilterBottomSheetDialogFragment (): BottomSheetDialogFragment(){

    private lateinit var filterAction:FilterAction
    private var _binding: FragmentFilterBottomSheetDialogListDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var SORT_BY = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            FragmentFilterBottomSheetDialogListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.priceHighToLow.setOnClickListener {
            filterAction.onClick(SortBy.HighToLow)
            dismiss()
        }
        binding.priceLowToHigh.setOnClickListener {
            filterAction.onClick(SortBy.LowToHigh)
            dismiss()

        }
        binding.resetFilters.setOnClickListener {
            filterAction.onClick(SortBy.NoFilter)
            dismiss()
        }
    }

    companion object {

        fun newInstance(): FilterBottomSheetDialogFragment =
            FilterBottomSheetDialogFragment()

        const val TAG:String = "FilterBottomSheetDialogFragment"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnActionCompleteListener(listener: FilterAction) {
        this.filterAction = listener
    }

}