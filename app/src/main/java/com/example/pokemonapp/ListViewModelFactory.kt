package com.example.pokemonapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.network.PokemonWebservice

class ListViewModelFactory(private val webservice: PokemonWebservice) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(webservice) as T
    }
}