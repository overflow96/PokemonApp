package com.example.pokemonapp

import android.net.Uri
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApi

fun PokemonApi.toPokemon() : Pokemon {
    val id = Uri.parse(url).lastPathSegment?.toInt()?: -1
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    return Pokemon(name, imageUrl, id)
}