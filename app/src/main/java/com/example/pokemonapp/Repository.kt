package com.example.pokemonapp

import com.example.pokemonapp.model.PokemonApi
import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.network.PokemonWebservice

class Repository(private val webservice: PokemonWebservice) {

    suspend fun getPokemonList(offset: Int, limit: Int) : ArrayList<PokemonApi> {
        val response = webservice.getPokemonList(offset, limit)
        val emptyArray = arrayListOf<PokemonApi>()
        if(response.isSuccessful){
            return response.body()?.results?: emptyArray
        }
        return emptyArray
    }

    suspend fun getPokemonDetail(id : Int) : PokemonDetail? {
        val response = webservice.getPokemonDetail(id)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}