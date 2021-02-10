package com.example.pokemonapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.*
import com.example.pokemonapp.data.PokemonListDataSourceFactory
import com.example.pokemonapp.data.Repository
import com.example.pokemonapp.network.PokemonWebservice
import com.example.pokemonapp.ui.adapter.PokemonListAdapter
import com.example.pokemonapp.ui.viewModel.PokemonListViewModelFactory
import com.example.pokemonapp.ui.viewModel.PokemonListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class PokemonListActivity : AppCompatActivity() {
    private lateinit var viewModel : PokemonListViewModel
    private val adapter = PokemonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webservice = PokemonWebservice()
        val pokemonListDataSourceFactory = PokemonListDataSourceFactory(webservice, lifecycleScope)

        val repository = Repository(webservice, pokemonListDataSourceFactory)

        viewModel = ViewModelProvider(this, PokemonListViewModelFactory(repository)).get(PokemonListViewModel::class.java)

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
