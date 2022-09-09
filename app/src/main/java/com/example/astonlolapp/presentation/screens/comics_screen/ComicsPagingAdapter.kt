package com.example.astonlolapp.presentation.screens.comics_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.astonlolapp.databinding.ComicsListElementBinding
import com.example.astonlolapp.domain.model.Comics

class ComicsPagingAdapter :
    PagingDataAdapter<Comics, ComicsViewHolder>(COMICS_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(
            ComicsListElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    companion object {
        private val COMICS_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comics>() {
            override fun areItemsTheSame(oldItem: Comics, newItem: Comics): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Comics, newItem: Comics): Boolean =
                oldItem == newItem
        }
    }
}