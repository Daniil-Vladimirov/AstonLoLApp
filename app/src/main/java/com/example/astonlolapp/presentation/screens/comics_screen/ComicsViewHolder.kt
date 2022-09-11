package com.example.astonlolapp.presentation.screens.comics_screen

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astonlolapp.databinding.ComicsListElementBinding
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.util.Constants.BASE_URL

class ComicsViewHolder(
    private val binding: ComicsListElementBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private var currentComics: Comics? = null

    fun bind(comics: Comics) {
        currentComics = comics
        binding.apply {
            comicsCoverImageView.load("$BASE_URL${comics.text[0]}")
            comicsCoverTextView.text = comics.cover
        }
    }

    init {
        binding.root.setOnClickListener {view->
            currentComics?.let { comics ->
                val action = FragmentComicsDirections.actionFragmentComicsToComposeUIFragment(comics.id)
                view.findNavController().navigate(action)
            }
        }
    }
}
