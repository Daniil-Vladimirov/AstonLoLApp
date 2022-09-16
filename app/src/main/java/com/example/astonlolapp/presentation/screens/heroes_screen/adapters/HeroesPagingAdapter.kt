package com.example.astonlolapp.presentation.screens.heroes_screen.adapters

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
import com.example.astonlolapp.presentation.screens.heroes_screen.ListScreenFragmentDirections
import com.example.astonlolapp.util.Constants
import timber.log.Timber

class HeroesPagingAdapter(private val listener: Listener) :
    PagingDataAdapter<Hero, HeroesPagingAdapter.HeroViewHolder>(HERO_DIFF_CALLBACK),
    View.OnClickListener {

    override fun onClick(v: View?) {
        Timber.d("onClickView")
        val hero = v?.tag as Hero
        if (v.id == R.id.add_to_favourite_ic) {
            listener.addToFavourite(hero = hero)
            listener.setColorAsFavourite(v, favouriteHero = hero)
        }
    }


    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        Timber.d("onBindViewHolder")
        val hero = getItem(position) ?: return
        holder.bind(hero)
        with(holder.binding){
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
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        Timber.d("onCreate")
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
        /**
         * Called when the user taps the "Delete" button in a list item
         */
        fun onHeroDelete(hero: Hero)

        /**
         * Called when the user taps the "Star" button in a list item.
         */
        fun addToFavourite(hero: Hero)
        fun setColorAsFavourite(view: View, favouriteHero: Hero)
    }

    class HeroViewHolder(
        val binding: HeroListElementBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentHero: Hero? = null

        fun bind(hero: Hero) {
            currentHero = hero
            binding.apply {
                heroImageView.load("${Constants.BASE_URL}${hero.image}")
                heroNameTextView.text = hero.name
                heroDescriptionTextView.text = hero.about
                adTextView.text = hero.ad.toString()
                apTextView.text = hero.ap.toString()
                winRateTextView.text = hero.winRate.toString()

            }
        }

        init {
            binding.heroImageView.setOnClickListener { view ->
                currentHero?.let { hero ->
                    val action =
                        ListScreenFragmentDirections.actionListScreenFragmentToDetailFragment(hero.id)
                    view.findNavController().navigate(action)
                }
            }
        }
    }
}