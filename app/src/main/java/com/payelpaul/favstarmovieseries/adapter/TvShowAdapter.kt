package com.payelpaul.favstarmovieseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payelpaul.favstarmovieseries.databinding.ArtistItemviewBinding

import com.payelpaul.favstarmovieseries.model.tvshow.TvShow

class TvShowAdapter():RecyclerView.Adapter<TvViewHolder>() {
    private val tvShowList = ArrayList<TvShow>()
    fun setList(tvShow:List<TvShow>){
        tvShowList.clear()
        tvShowList.addAll(tvShow)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
      val binding = ArtistItemviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int {
       return tvShowList.size
    }
}
class TvViewHolder(val binding: ArtistItemviewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(artistList: TvShow){
        binding.titleTextView.text = artistList.name

        val posterURL = "https://image.tmdb.org/t/p/w500"+artistList.posterPath
        Glide.with(binding.imageView.context).load(posterURL).into(binding.imageView)
    }
}