package com.example.pokemonapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApi
import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.network.PokemonWebservice

class MainViewModel(private val webservice: PokemonWebservice) : ViewModel() {
    private val pokemonListLiveData = MutableLiveData<ArrayList<PokemonApi>>()
    private val pokemonLiveData = MutableLiveData<PokemonDetail>()
    private val liveData : LiveData<PagedList<Pokemon>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        liveData  = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Pokemon> {

        val dataSourceFactory = object : DataSource.Factory<Int, Pokemon>() {
            override fun create(): DataSource<Int, Pokemon> {
                return PokemonListDataSource(webservice, viewModelScope)
            }
        }
        return LivePagedListBuilder<Int, Pokemon>(dataSourceFactory, config)
    }

    val pokemonList : LiveData<PagedList<Pokemon>>
        get() = liveData



    /*fun fetchPokemonDetail(id: Int){
        viewModelScope.launch {
            val pokemon = repository.getPokemonDetail(id)
            pokemonLiveData.value = pokemon
        }
    }*/
}