package com.example.pokemonapp.network

import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.model.PokemonListResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("api/v2//pokemon")
    suspend fun getPokemonList(@Query("offset") offset : Int, @Query("limit") limit : Int) : Response<PokemonListResult>

    @GET("api/v2//pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id : Int) : Response<PokemonDetail>
}