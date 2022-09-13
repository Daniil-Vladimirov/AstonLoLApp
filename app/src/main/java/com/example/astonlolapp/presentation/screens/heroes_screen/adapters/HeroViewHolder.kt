package com.example.astonlolapp.presentation.screens.heroes_screen.adapters

/*
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
        binding.heroImageView.setOnClickListener { view ->
            currentHero?.let { hero ->
                val action =
                    ListScreenFragmentDirections.actionListScreenFragmentToDetailFragment(hero.id)
                view.findNavController().navigate(action)
            }
        }
    }
}*/
