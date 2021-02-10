package com.example.pokemonapp.data

import androidx.paging.PageKeyedDataSource
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.network.PokemonWebservice
import com.example.pokemonapp.toPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonListDataSource(private val webservice: PokemonWebservice, private val coroutineScope: CoroutineScope) : PageKeyedDataSource<Int, Pokemon>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Pokemon>) {
        coroutineScope.launch {
            val pokemonResponse = webservice.getPokemonList(0,10)
            val pokemonApiList = pokemonResponse.body()?.results
            val pokemonList = pokemonApiList?.map {
                it.toPokemon()
            }
            if(pokemonResponse.isSuccessful)
                callback.onResult(pokemonList?: arrayListOf(), null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        coroutineScope.launch {
            val pokemonResponse = webservice.getPokemonList((params.key -1) *10, 10)
            val pokemonApiList = pokemonResponse.body()?.results
            val pokemonList = pokemonApiList?.map {
                it.toPokemon()
            }
            if(pokemonResponse.isSuccessful){
                callback.onResult(pokemonList?: arrayListOf(), params.key +1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {}
}