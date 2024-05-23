package com.example.employeedirectory.core.di

import com.example.employeedirectory.core.data.NullableTypAdapterFactory
import com.example.employeedirectory.core.network.AmazonawsAPI
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(NullableTypAdapterFactory())
            .create()

        return Retrofit
            .Builder()
            .baseUrl(Constants.AMAZONAWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideDispatchers() = Dispatchers.IO


    @Singleton
    @Provides
    fun provideOpenMovieDbClient(retrofit: Retrofit): AmazonawsAPI =
        retrofit.create(AmazonawsAPI::class.java)
}

object Constants {
    const val AMAZONAWS_BASE_URL = "https://s3.amazonaws.com/"
}