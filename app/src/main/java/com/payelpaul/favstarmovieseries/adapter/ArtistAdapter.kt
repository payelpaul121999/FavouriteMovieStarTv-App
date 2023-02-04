package com.payelpaul.favstarmovieseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payelpaul.favstarmovieseries.databinding.ArtistItemviewBinding
import com.payelpaul.favstarmovieseries.databinding.ItemShimmerLyoutBinding
import com.payelpaul.favstarmovieseries.model.artist.Artist
import com.payelpaul.favstarmovieseries.model.artist.ArtistDataType
import com.payelpaul.favstarmovieseries.model.artist.ArtistDto
import com.payelpaul.favstarmovieseries.model.artist.PopularArtistList

class ArtistAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     private val artistList = ArrayList<Artist>()
        fun setList(artist: List<Artist>){
            artistList.clear()
            artistList.addAll(artist)
            notifyDataSetChanged()
        }

    fun setShimmerList(){
        artistList.apply {
            clear()
            addAll(artistShimmerList)
        }
        notifyDataSetChanged()
    }


    inner class ShimmerViewHolder(val binding: ItemShimmerLyoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    inner class ArtistViewHolder(val binding:ArtistItemviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(artistList: ArtistDto){
            binding.titleTextView.text = artistList.name

            val posterURL = "https://image.tmdb.org/t/p/w500"+artistList.profile_path
            Glide.with(binding.imageView.context).load(posterURL).into(binding.imageView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val currentItem  = artistList.get(position)

        when(holder){
            is ShimmerViewHolder ->{

            }
            is ArtistViewHolder ->{
                currentItem.artistDto?.let {
                    holder.bind(
                        it
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return artistList.size
    }

    override fun getItemViewType(position: Int): Int {
        return artistList.get(position).artistDataType.value
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         when(viewType){
            ArtistDataType.SHIMMER.value->{
                val binding = ItemShimmerLyoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ShimmerViewHolder(binding)
            }
            else ->{
                val binding = ArtistItemviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ArtistViewHolder(binding)
            }
        }
    }

    companion object{
        private val artistShimmerList = listOf(
            Artist(artistDataType = ArtistDataType.SHIMMER),
            Artist(artistDataType = ArtistDataType.SHIMMER),
            Artist(artistDataType = ArtistDataType.SHIMMER)
        )
    }

}
