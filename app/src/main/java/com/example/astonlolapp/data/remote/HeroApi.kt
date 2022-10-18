package com.example.astonlolapp.data.remote

import com.example.astonlolapp.domain.model.ApiRequest
import com.example.astonlolapp.domain.model.ApiResponse
import com.example.astonlolapp.domain.model.UpdateInfo
import retrofit2.http.*

interface HeroApi {

    @GET("/lol/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 3
    ): ApiResponse

    @GET("lol/comics")
    suspend fun getComics(): ApiResponse

    @POST("/token_verification")
    suspend fun tokenVerification(
        @Body apiRequest: ApiRequest
    ): ApiResponse

    @DELETE("/delete_user")
    suspend fun deleteUser(): ApiResponse

    @PUT("/update_user")
    suspend fun updateUserInfo(
        @Body userInfoUpdate: UpdateInfo
    ): ApiResponse

    @GET("/sign_out")
    suspend fun signOut(): ApiResponse

    @GET("/get_user")
    suspend fun getUser(): ApiResponse

}