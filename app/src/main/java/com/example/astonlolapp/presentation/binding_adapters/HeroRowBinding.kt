package com.example.astonlolapp.presentation.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.astonlolapp.R

class HeroRowBinding {

    companion object{

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

    }
}
