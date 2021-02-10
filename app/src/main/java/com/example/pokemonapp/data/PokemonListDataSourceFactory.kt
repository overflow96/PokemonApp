package com.example.pokemonapp.data

import androidx.paging.DataSource
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.network.PokemonWebservice
import kotlinx.coroutines.CoroutineScope

class PokemonListDataSourceFactory(private val webservice: PokemonWebservice, private val coroutineScope: CoroutineScope) : DataSource.Factory<Int, Pokemon>() {
    override fun create(): DataSource<Int, Pokemon> {
        return PokemonListDataSource(webservice, coroutineScope)
    }
}