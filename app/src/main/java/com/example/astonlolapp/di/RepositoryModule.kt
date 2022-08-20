package com.example.astonlolapp.di

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroes = SearchHeroesUseCase(repository = repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository)
        )
    }

}