package com.example.pokemonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApi
import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.network.PokemonWebservice
import kotlinx.coroutines.CoroutineScope

class Repository(private val webservice: PokemonWebservice, private val coroutineScope: CoroutineScope) {
    private val liveData : LiveData<PagedList<Pokemon>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        liveData  = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Pokemon> {

        val dataSourceFactory = object : DataSource.Factory<Int, Pokemon>() {
            override fun create(): DataSource<Int, Pokemon> {
                return PokemonListDataSource(webservice, coroutineScope)
            }
        }
        return LivePagedListBuilder<Int, Pokemon>(dataSourceFactory, config)
    }

    val pokemonList : LiveData<PagedList<Pokemon>>
        get() = liveData

    suspend fun getPokemonDetail(id : Int) : PokemonDetail? {
        val response = webservice.getPokemonDetail(id)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}