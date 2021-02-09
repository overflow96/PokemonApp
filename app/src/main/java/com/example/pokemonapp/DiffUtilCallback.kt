package com.example.pokemonapp

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApi

class DiffUtilCallBack : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

}