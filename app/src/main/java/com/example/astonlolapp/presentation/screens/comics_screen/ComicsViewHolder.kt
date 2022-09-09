package com.example.astonlolapp.presentation.screens.comics_screen

import androidx.recyclerview.widget.RecyclerView
import com.example.astonlolapp.databinding.ComicsListElementBinding
import com.example.astonlolapp.domain.model.Comics

class ComicsViewHolder(
    private val binding: ComicsListElementBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private var currentComics: Comics? = null

    fun bind(comics: Comics) {
        currentComics = comics
        binding.apply {

        }
    }

    init {
        binding.root.setOnClickListener { view ->

        }
    }
}
