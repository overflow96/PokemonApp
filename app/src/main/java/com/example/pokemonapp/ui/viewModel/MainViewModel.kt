package com.example.pokemonapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pokemonapp.data.PokemonListDataSource
import com.example.pokemonapp.data.Repository
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApi
import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.network.PokemonWebservice

class MainViewModel(private val repository: Repository) : ViewModel() {
    val pokemonList : LiveData<PagedList<Pokemon>>
        get() = repository.pokemonList
}