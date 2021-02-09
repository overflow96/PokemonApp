package com.example.pokemonapp.network

import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.model.PokemonListResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonWebservice() {
    private val pokemonApi by lazy {
        Retrofit.Builder()
        .baseUrl("https://pokeapi.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)
    }

    suspend fun getPokemonList(offset : Int, limit : Int) : Response<PokemonListResult> = pokemonApi.getPokemonList(offset, limit)

    suspend fun getPokemonDetail(id : Int) : Response<PokemonDetail> = pokemonApi.getPokemonDetail(id)
}