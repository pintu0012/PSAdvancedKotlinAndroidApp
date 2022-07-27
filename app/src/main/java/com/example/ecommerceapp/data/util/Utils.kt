package com.example.ecommerceapp.data.util

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

object Utils {

    public fun Fragment.showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
            show()
        }
    }

}