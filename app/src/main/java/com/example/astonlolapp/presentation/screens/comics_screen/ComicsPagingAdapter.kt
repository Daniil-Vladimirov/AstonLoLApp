package com.example.astonlolapp.presentation.screens.comics_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astonlolapp.databinding.ComicsListElementBinding
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.util.Constants.BASE_URL

class ComicsPagingAdapter :
    PagingDataAdapter<Comics, ComicsPagingAdapter.ComicsViewHolder>(COMICS_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ComicsListElementBinding.inflate(inflater, parent, false)
        return ComicsViewHolder(binding = binding)
    }


    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = getItem(position) ?: return
        with(holder.binding) {
            comicsCoverImageView.load("${BASE_URL}${comics.text[0]}")
            comicsCoverTextView.text = comics.cover
            root.setOnClickListener {view->
                comics.let { comics ->
                    val action = ComicsFragmentDirections.actionFragmentComicsToComposeUIFragment(comics.id)
                    view.findNavController().navigate(action)
                }
            }
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

    class ComicsViewHolder(
        val binding: ComicsListElementBinding,
    ) : RecyclerView.ViewHolder(binding.root)

}