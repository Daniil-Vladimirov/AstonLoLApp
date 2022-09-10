package com.example.astonlolapp.presentation.screens.list_screen

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astonlolapp.databinding.HeroListElementBinding
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.util.Constants.BASE_URL

class HeroViewHolder(
    private val binding: HeroListElementBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private var currentHero: Hero? = null

    fun bind(hero: Hero) {
        currentHero = hero
        binding.apply {
            heroImageView.load("$BASE_URL${hero.image}")
            heroNameTextView.text = hero.name
            heroDescriptionTextView.text = hero.about
            adTextView.text = hero.ad.toString()
            apTextView.text = hero.ap.toString()
            winRateTextView.text = hero.winRate.toString()
        }
    }

    init {
        binding.root.setOnClickListener {view->
            currentHero?.let { hero ->
                val action = ListScreenFragmentDirections.actionListScreenFragmentToDetailFragment(hero.id)
                view.findNavController().navigate(action)
            }
        }
    }
}
