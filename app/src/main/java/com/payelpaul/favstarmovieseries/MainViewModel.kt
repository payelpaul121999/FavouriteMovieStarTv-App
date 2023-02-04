package com.payelpaul.favstarmovieseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payelpaul.favstarmovieseries.model.artist.PopularArtistList
import com.payelpaul.favstarmovieseries.model.movie.MovieList
import com.payelpaul.favstarmovieseries.model.tvshow.TvShowList
import com.payelpaul.favstarmovieseries.repository.UserRepository
import com.payelpaul.favstarmovieseries.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {

    var currentIndexOnRecyclerView: Int = 0
    private var data: NetworkResult<PopularArtistList?> ?= null
    private var dataM: NetworkResult<MovieList?> ?= null

   // val movieLiveData:LiveData<NetworkResult<MovieList>> get() = userRepository.uMovieData

    val tvShowLiveData:LiveData<NetworkResult<TvShowList>> get() = userRepository.uShowList
    private val _userLiveData = MutableLiveData<NetworkResult<PopularArtistList?>>()
    private val _userLiveDataMovie = MutableLiveData<NetworkResult<MovieList?>>()
    val artistList:LiveData<NetworkResult<PopularArtistList?>> get() = _userLiveData
     val movieLiveData:LiveData<NetworkResult<MovieList?>> get() = _userLiveDataMovie
    fun getAllPopularArtistList(){
        viewModelScope.launch {
            if(data == null){
                data =  userRepository.getAllPopularArtistList()
                delay(1000)
            }

            _userLiveData.postValue(data!!)
        }
    }

    fun getAllTvShowList(){
        viewModelScope.launch {
            userRepository.getAllTvShowList()
        }
    }
    fun getAllMovieList(){
        viewModelScope.launch {
            //userRepository.getAllMovieList()
            if(dataM==null){
                dataM = userRepository.getAllMovieList()
            }
            _userLiveDataMovie.postValue(dataM!!)
        }
    }



}