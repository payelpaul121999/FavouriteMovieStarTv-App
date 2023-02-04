package com.payelpaul.favstarmovieseries.model.artist

import com.google.gson.annotations.SerializedName
import com.payelpaul.favstarmovieseries.model.artist.Artist

data class PopularArtistList(
    @SerializedName("results")
    val results: List<ArtistDto>,
)
