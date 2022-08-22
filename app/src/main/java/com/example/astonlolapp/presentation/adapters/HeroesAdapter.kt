package com.example.astonlolapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.astonlolapp.databinding.HeroListElementBinding
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.util.HeroDiffUtil

class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.MyViewHolder>() {

    private var heroes = emptyList<Hero>()

    class MyViewHolder(private val binding: HeroListElementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Hero){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeroListElementBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = heroes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    fun setData(newData: List<Hero>){
        val recipesDiffUtil =
            HeroDiffUtil(heroes, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        heroes = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}