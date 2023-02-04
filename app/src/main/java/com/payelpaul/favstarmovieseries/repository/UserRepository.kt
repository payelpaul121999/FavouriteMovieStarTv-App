package com.payelpaul.favstarmovieseries.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.payelpaul.favstarmovieseries.utils.Constant
import com.payelpaul.favstarmovieseries.utils.NetworkResult
import com.payelpaul.favstarmovieseries.api.UserApi
import com.payelpaul.favstarmovieseries.model.artist.PopularArtistList
import com.payelpaul.favstarmovieseries.model.movie.MovieList
import com.payelpaul.favstarmovieseries.model.tvshow.TvShowList
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {



    private val _uMovieData= MutableLiveData<NetworkResult<MovieList>>()
    val uMovieData:LiveData<NetworkResult<MovieList>> get() = _uMovieData

    private val _uShowList = MutableLiveData<NetworkResult<TvShowList>>()
    val  uShowList : LiveData<NetworkResult<TvShowList>> get() = _uShowList

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val status : LiveData<NetworkResult<Pair<Boolean, String>>> get() = _statusLiveData

    suspend fun getAllPopularArtistList(): NetworkResult<PopularArtistList?> {
        val responsePopular = userApi.getAllPopularArtistList(Constant.Base.APIKEY)
       return if (responsePopular.isSuccessful && responsePopular.body()!=null){
            val response = responsePopular.body()
            NetworkResult.Success(response)
        }else if (responsePopular.errorBody()!=null){
            NetworkResult.Error("Error",null)
        }else{
            NetworkResult.Error("Error",null)
        }
    }



    suspend fun getAllMovieList() : NetworkResult<MovieList?>{
        _uMovieData.postValue(NetworkResult.Loading())
      val response = userApi.getPopularMovies(Constant.APIKEY)
       return if (response.isSuccessful && response.body()!=null){
            //_uMovieData.postValue(NetworkResult.Success(response.body()!!))
           NetworkResult.Success(response.body()!!)
        }else if (response.errorBody()!=null){
            //_uMovieData.postValue(NetworkResult.Error("Error"))
           NetworkResult.Error("Error",null)
        }else{
           // _uMovieData.postValue(NetworkResult.Error("Error"))
           NetworkResult.Error("Error",null)
        }

    }

suspend fun getAllTvShowList(){
    _uShowList.postValue(NetworkResult.Loading())
    val responseTvShow=userApi.getPopularTvShow(Constant.APIKEY)
    if (responseTvShow.isSuccessful && responseTvShow.body()!=null){
        _uShowList.postValue(NetworkResult.Success(responseTvShow.body()!!))
        // handleResponse(responsePopular,"Successfully get response..")
    }else if (responseTvShow.errorBody()!=null){
        _uShowList.postValue(NetworkResult.Error("Error"))
    }else{
        _uShowList.postValue(NetworkResult.Error("Error"))
    }

}



    fun handleResponse(response:Response<PopularArtistList>,message:String){
        if (response.isSuccessful && response.body()!=null){
            _statusLiveData.postValue(NetworkResult.Success(Pair(true,message)))
        }else{
            _statusLiveData.postValue(NetworkResult.Success(Pair(false,"Something went to wrong.")))
        }
    }

}