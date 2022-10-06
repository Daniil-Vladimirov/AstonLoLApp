package com.example.astonlolapp.data.remote

import com.example.astonlolapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {

    @GET("/lol/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 3
    ): ApiResponse

    @GET("lol/comics")
    suspend fun getComics(): ApiResponse

}