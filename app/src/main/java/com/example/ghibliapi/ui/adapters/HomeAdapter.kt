package com.example.ghibliapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ghibliapi.data.model.Film
import com.example.ghibliapi.databinding.ItemFilmBinding
import com.example.ghibliapi.ui.fragments.home.HomeFragment
import com.squareup.picasso.Picasso

class HomeAdapter(
    private val films: MutableList<Film> = mutableListOf(),
    private val context: HomeFragment,
    var onFilmClick: (film: Film) -> Unit = {}
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(val itemBinding: ItemFilmBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal fun render(film: Film) {
            Picasso.get().load(film.image).into(itemBinding.filmBanner)

            itemView.setOnClickListener {
                onFilmClick(film)
            }
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(films[position])
    }

    fun update(list: List<Film>) {
        this.films.clear()
        this.films.addAll(list)
    }

}