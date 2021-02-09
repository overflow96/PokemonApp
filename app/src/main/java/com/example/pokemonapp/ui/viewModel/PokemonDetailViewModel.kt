package com.example.pokemonapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.Repository
import com.example.pokemonapp.model.PokemonDetail
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val repository: Repository) : ViewModel() {
    private val pokemonLiveData = MutableLiveData<PokemonDetail>()

    val pokemonDetail : LiveData<PokemonDetail>
        get() = pokemonLiveData

    fun fetchPokemonDetail(id: Int){
        viewModelScope.launch {
            val pokemon = repository.getPokemonDetail(id)
            pokemonLiveData.value = pokemon
        }
    }
}