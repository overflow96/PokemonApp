package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class PokemonListResult(val next : String, val results : ArrayList<PokemonApi>)

data class PokemonApi (val name : String, val url : String)

data class Pokemon(val name : String, val imageUrl : String, val id : Int)

data class PokemonDetail(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("weight")
    val weight : Int,
    @SerializedName("height")
    val height : Int,
    @SerializedName("base_experience")
    val baseExperience : Int,
    @SerializedName("sprites")
    val sprites : PokemonSprite,
    @SerializedName("types")
    val types : ArrayList<PokemonTypeHolder>,
    @SerializedName("stats")
    val stats : ArrayList<PokemonStatHolder>,
    @SerializedName("abilities")
    val abilities : ArrayList<PokemonAbilityHolder>)

data class PokemonAbilityHolder(
    @SerializedName("is_hidden")
    val isHidden : Boolean,
    @SerializedName("ability")
    val ability : PokemonAbility)

data class PokemonAbility(
    @SerializedName("name")
    val name: String
)

data class PokemonSprite(
    @SerializedName("back_default")
    val backDefault : String,
    @SerializedName("front_default")
    val frontDefault : String)

data class PokemonType(
    @SerializedName("name")
    val name : String,
    @SerializedName("url")
    val url : String
)

data class PokemonTypeHolder(
    @SerializedName("slot")
    val slot : Int,
    @SerializedName("type")
    val type : PokemonType
)

data class PokemonStatHolder(
    @SerializedName("base_stat")
    val baseStat : Int,
    @SerializedName("stat")
    val stat : PokemonStat
)

data class PokemonStat(
    @SerializedName("name")
    val name : String,
    @SerializedName("url")
    val url : String
)


