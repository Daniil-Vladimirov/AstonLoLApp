package com.example.astonlolapp.presentation.screens.heroes_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.astonlolapp.databinding.HeroListElementBinding
import com.example.astonlolapp.domain.model.Hero

class HeroesPagingAdapter() :
    PagingDataAdapter<Hero, HeroViewHolder>(HERO_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder =
        HeroViewHolder(
            HeroListElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    companion object {
        private val HERO_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean =
                oldItem == newItem
        }
    }
}