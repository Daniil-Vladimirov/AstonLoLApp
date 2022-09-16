package com.example.astonlolapp.presentation.screens.heroes_screen

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.HeroListElementBinding
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.util.Constants

class HeroesPagingAdapter(private val listener: Listener) :
    PagingDataAdapter<Hero, HeroesPagingAdapter.HeroViewHolder>(HERO_DIFF_CALLBACK),
    View.OnClickListener {

    override fun onClick(v: View?) {
        val hero = v?.tag as Hero
        if (v.id == R.id.add_to_favourite_ic) {
            listener.addDeleteFromFavourite(hero = hero)
        }
    }


    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = getItem(position) ?: return
        with(holder.binding) {
            root.tag = hero
            addToFavouriteIc.tag = hero
            addToFavouriteIc.setImageResource(
                if (hero.isFavourite == true)
                    R.drawable.ic_favourite
                else
                    R.drawable.ic_not_favourite
            )
            val tintColor = if (hero.isFavourite == true)
                R.color.yellow
            else
                R.color.lightGray
            addToFavouriteIc.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(root.context, tintColor)
            )
            heroImageView.load("${Constants.BASE_URL}${hero.image}")
            heroNameTextView.text = hero.name
            heroDescriptionTextView.text = hero.about
            adTextView.text = hero.ad.toString()
            apTextView.text = hero.ap.toString()
            winRateTextView.text = hero.winRate.toString()
            heroImageView.setOnClickListener { view ->
                hero.let { hero ->
                    val action =
                        ListScreenFragmentDirections.actionListScreenFragmentToDetailFragment(hero.id)
                    view.findNavController().navigate(action)
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HeroListElementBinding.inflate(inflater, parent, false)
        binding.addToFavouriteIc.setOnClickListener(this)
        return HeroViewHolder(binding = binding)
    }

    companion object {
        private val HERO_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean =
                oldItem == newItem
        }
    }

    interface Listener {
        fun addDeleteFromFavourite(hero: Hero)
    }

    class HeroViewHolder(
        val binding: HeroListElementBinding,
    ) : RecyclerView.ViewHolder(binding.root)


}