package com.payelpaul.favstarmovieseries.api

import com.payelpaul.favstarmovieseries.model.artist.PopularArtistList
import com.payelpaul.favstarmovieseries.model.movie.MovieList
import com.payelpaul.favstarmovieseries.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("person/popular")
    suspend fun getAllPopularArtistList(@Query("api_key") apiKey:String):Response<PopularArtistList>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShow(@Query("api_key") apiKey:String): Response<TvShowList>



}