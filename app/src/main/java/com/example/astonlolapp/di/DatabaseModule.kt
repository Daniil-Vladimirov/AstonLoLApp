package com.example.astonlolapp.di

import android.content.Context
import androidx.room.Room
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.repository.LocalDataSourceImp
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs
import com.example.astonlolapp.util.Constants.HERO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): HeroDatabase {
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java,
            HERO_DATABASE
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: HeroDatabase
    ): LocalDatasourceAbs {
        return LocalDataSourceImp(
            dataBase = database
        )
    }

}