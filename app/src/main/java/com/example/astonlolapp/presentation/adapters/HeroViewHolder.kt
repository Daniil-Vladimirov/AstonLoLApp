package com.example.astonlolapp.presentation.adapters

import android.os.Build.VERSION_CODES.BASE
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.HeroListElementBinding
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.util.Constants.BASE_URL
import timber.log.Timber

class HeroViewHolder(
    private val binding: HeroListElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hero: Hero) {
        binding.apply {
            heroImageView.load("$BASE_URL${hero.image}")
            heroNameTextView.text = hero.name
            heroDescriptionTextView.text = hero.about
            adTextView.text = hero.ad.toString()
            apTextView.text = hero.ap.toString()
            winRateTextView.text = hero.winRate.toString()
        }
        Timber.d(hero.image)
    }
}