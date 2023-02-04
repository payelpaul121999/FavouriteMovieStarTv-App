package com.payelpaul.favstarmovieseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payelpaul.favstarmovieseries.databinding.ArtistItemviewBinding
import com.payelpaul.favstarmovieseries.model.movie.Movie


class MovieAdapter():RecyclerView.Adapter<ViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setList(artist:List<Movie>){
        movieList.clear()
        movieList.addAll(artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ArtistItemviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
       return movieList.size
    }
}
class ViewHolder(val binding: ArtistItemviewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(artistList: Movie){
        binding.titleTextView.text = artistList.title

        val posterURL = "https://image.tmdb.org/t/p/w500"+artistList.posterPath
        Glide.with(binding.imageView.context).load(posterURL).into(binding.imageView)
    }
}