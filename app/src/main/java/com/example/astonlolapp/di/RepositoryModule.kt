package com.example.astonlolapp.di

import android.content.Context
import com.example.astonlolapp.data.repository.DataStoreOperationsImpl
import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.repository.DataStoreOperationsAbs
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.domain.use_cases.comisc.get_comics.GetComicsFromApiUseCase
import com.example.astonlolapp.domain.use_cases.comisc.get_comics.GetComicsUseCase
import com.example.astonlolapp.domain.use_cases.comisc.get_selected_comics.GetSelectedComicsUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.add_favourite_hero.AddFavouriteHeroUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.delete_all_favourite_heroes.DeleteAllFavouriteHeroesUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.delete_favourite_hero.DeleteFavouriteHeroUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.get_all_favourite_heroes.GetAllFavouriteHeroesUseCase
import com.example.astonlolapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.read_onboarding.ReadOnboardingUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.save_onboarding.SaveOnboardingStateUseCase
import com.example.astonlolapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDataPreferences(@ApplicationContext context: Context): DataStoreOperationsAbs {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroes = SearchHeroesUseCase(repository = repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository),
            saveOnboardingStateUseCase = SaveOnboardingStateUseCase(repository = repository),
            readOnboardingUseCase = ReadOnboardingUseCase(repository = repository),
            getComicsUseCase = GetComicsUseCase(repository = repository),
            getSelectedComicsUseCase = GetSelectedComicsUseCase(repository = repository),
            getComicsFromApiUseCase = GetComicsFromApiUseCase(repository = repository),
            addFavouriteHeroes = AddFavouriteHeroUseCase(repository = repository),
            deleteAllFavouriteHeroes = DeleteAllFavouriteHeroesUseCase(repository = repository),
            getAllFavouriteHeroesUseCase = GetAllFavouriteHeroesUseCase(repository = repository),
            deleteFavouriteHeroUseCase = DeleteFavouriteHeroUseCase(repository = repository),
        )
    }

}