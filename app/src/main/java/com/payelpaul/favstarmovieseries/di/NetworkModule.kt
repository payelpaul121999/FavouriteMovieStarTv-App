package com.payelpaul.favstarmovieseries.di

import com.payelpaul.favstarmovieseries.utils.Constant
import com.payelpaul.favstarmovieseries.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit.Builder{
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofit: Retrofit.Builder): UserApi {
        return retrofit.build().create(UserApi::class.java)
    }

}