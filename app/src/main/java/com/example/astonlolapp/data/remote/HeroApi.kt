package com.example.astonlolapp.data.remote

import com.example.astonlolapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {

    @GET("/lol/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("lol/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse

}