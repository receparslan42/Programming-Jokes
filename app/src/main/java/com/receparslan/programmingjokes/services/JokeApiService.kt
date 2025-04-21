package com.receparslan.programmingjokes.services

import com.receparslan.programmingjokes.model.Jokes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://v2.jokeapi.dev"

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface JokeApiService {
    @GET("/joke/Programming?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&amount=10")
    suspend fun getJAllJoke(): Response<Jokes>
}

@Suppress("unused", "RedundantSuppression")
object JokeAPI {
    val retrofitService: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }
}
