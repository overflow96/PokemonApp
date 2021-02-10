package com.example.pokemonapp.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.network.PokemonWebservice

class Repository(private val webservice: PokemonWebservice, pokemonListDataSourceFactory: PokemonListDataSourceFactory) {
    val pokemonList : LiveData<PagedList<Pokemon>>

    private companion object{
        const val PAGE_SIZE = 10
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        pokemonList  = LivePagedListBuilder<Int, Pokemon>(pokemonListDataSourceFactory, config).build()
    }

    suspend fun getPokemonDetail(id : Int) : PokemonDetail? {
        val response = webservice.getPokemonDetail(id)
        return if(response.isSuccessful) response.body() else null
    }
}