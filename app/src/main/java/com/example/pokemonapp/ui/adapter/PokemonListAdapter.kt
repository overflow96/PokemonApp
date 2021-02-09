package com.example.pokemonapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.ui.PokemonDetailActivity
import kotlinx.android.synthetic.main.adapter_item.view.*

class PokemonListAdapter : PagedListAdapter<Pokemon, PokemonListAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPokemon(it) }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonName: TextView = itemView.pokemonName
        private val pokemonImage: ImageView = itemView.pokemonImage

        fun bindPokemon(pokemon : Pokemon){
            pokemonName.text = pokemon.name.capitalize()
            Glide.with(pokemonImage.context).load(pokemon.imageUrl).into(pokemonImage)
            setFadeAnimation(itemView)
            itemView.setOnClickListener {
                //TODO: Send to details activity
                Toast.makeText(itemView.context, "pressed pokemon ${pokemon.name} with id ${pokemon.id}", Toast.LENGTH_LONG).show()
                val intent = Intent(itemView.context, PokemonDetailActivity::class.java).apply {
                    putExtra("id", pokemon.id)
                }
                startActivity(itemView.context, intent, null)
            }
        }

        private fun setFadeAnimation(view : View){
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 500
            view.startAnimation(anim)
        }
    }
}