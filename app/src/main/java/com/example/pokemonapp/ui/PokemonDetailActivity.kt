package com.example.pokemonapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.data.PokemonListDataSourceFactory
import com.example.pokemonapp.data.Repository
import com.example.pokemonapp.network.PokemonWebservice
import com.example.pokemonapp.ui.viewModel.DetailViewModelFactory
import com.example.pokemonapp.ui.viewModel.PokemonDetailViewModel
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {
    private lateinit var viewModel : PokemonDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        val id = intent.extras?.getInt("id")?: -1

        val webservice = PokemonWebservice()
        val pokemonListDataSourceFactory = PokemonListDataSourceFactory(webservice, lifecycleScope)

        val repository = Repository(webservice, pokemonListDataSourceFactory)

        viewModel = ViewModelProvider(this, DetailViewModelFactory(repository)).get(PokemonDetailViewModel::class.java)

        viewModel.fetchPokemonDetail(id)

        viewModel.pokemonDetail.observe(this, Observer {
            pokemonName.text = it.name.capitalize()
            pokemonHeight.text = it.height.toString()
            pokemonWeight.text = it.weight.toString()
            Glide.with(this).load(it.sprites.frontDefault).centerCrop().into(pokemonImageFront)
            Glide.with(this).load(it.sprites.backDefault).centerCrop().into(pokemonImageBack)
        })
    }
}
