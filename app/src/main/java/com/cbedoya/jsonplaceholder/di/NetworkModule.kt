package com.cbedoya.jsonplaceholder.di

import com.cbedoya.jsonplaceholder.networking.PostService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private val baseUrl: String) {

    @Provides
    @Reusable
    fun providePostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)

    @Provides
    @Reusable
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}
