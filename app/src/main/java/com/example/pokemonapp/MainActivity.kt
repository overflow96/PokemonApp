package com.example.pokemonapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApi
import com.example.pokemonapp.network.PokemonWebservice
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var repository: Repository
    private val adapter = PokemonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(PokemonWebservice())

        viewModel = ViewModelProvider(this, ListViewModelFactory(PokemonWebservice())).get(MainViewModel::class.java)

        viewModel.pokemonList.observe(this, Observer {
            adapter.submitList(it)
        })

        //var id = Uri.parse(pokemon.url).lastPathSegment Ir buscar o id

        initializeList()
    }

    private fun initializeList() {
        pokemonRecycler.layoutManager = GridLayoutManager(this, 3)
        pokemonRecycler.adapter = adapter
    }
}
