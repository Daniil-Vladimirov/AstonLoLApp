package com.example.astonlolapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.HeroListElementBinding
import com.example.astonlolapp.domain.model.Hero
import timber.log.Timber

class HeroAdapter : PagingDataAdapter<Hero, HeroAdapter.Holder>(UsersDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Timber.d("onBindingViewHolder is called")
        val hero = getItem(position) ?: return
        with(holder.binding) {
            heroNameTextView.text = hero.name
            heroDescriptionTextView.text = hero.about
            adTextView.text = hero.ad.toString()
            apTextView.text = hero.ap.toString()
            winRateTextView.text = hero.winRate.toString()
            loadHeroImage(heroImageView, hero.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Timber.d("onCreateViewHolder is called")
        val inflater = LayoutInflater.from(parent.context)
        val binding = HeroListElementBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    private fun loadHeroImage(imageView: ImageView, url: String) {
        Timber.d("LoadImage is called")
        imageView.load(url) {
            // placeholder image is the image used
            // when our image url fails to load.
            placeholder(R.drawable.ic_error_placeholder)
        }
    }

    class Holder(
        val binding: HeroListElementBinding
    ) : RecyclerView.ViewHolder(binding.root)

}

// ---

class UsersDiffCallback : DiffUtil.ItemCallback<Hero>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}