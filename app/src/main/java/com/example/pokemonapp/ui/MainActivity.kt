package com.example.pokemonapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.*
import com.example.pokemonapp.data.Repository
import com.example.pokemonapp.network.PokemonWebservice
import com.example.pokemonapp.ui.adapter.PokemonListAdapter
import com.example.pokemonapp.ui.viewModel.ListViewModelFactory
import com.example.pokemonapp.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private val adapter = PokemonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository(PokemonWebservice(), lifecycleScope)

        viewModel = ViewModelProvider(this, ListViewModelFactory(repository)).get(MainViewModel::class.java)

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
