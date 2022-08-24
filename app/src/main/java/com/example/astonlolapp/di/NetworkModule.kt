package com.example.astonlolapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.data.repository.RemoteDataSourceImpl
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import com.example.astonlolapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType = contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideHeroApi(retrofit: Retrofit): HeroApi {
        return retrofit.create(HeroApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDatasource(
        heroApi: HeroApi,
        heroDatabase: HeroDatabase
    ): RemoteDatasourceAbs {
        return RemoteDataSourceImpl(
            heroApi = heroApi,
            heroDatabase =heroDatabase
        )
    }
}