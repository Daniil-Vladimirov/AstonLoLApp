package com.example.astonlolapp.di

import android.content.Context
import com.example.astonlolapp.data.repository.DataStoreOperationsImpl
import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.repository.DataStoreOperationsAbs
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.domain.use_cases.comics.get_comics.GetComicsFromApiUseCase
import com.example.astonlolapp.domain.use_cases.comics.get_comics.GetComicsFromCacheUseCase
import com.example.astonlolapp.domain.use_cases.comics.get_selected_comics.GetSelectedComicsUseCase
import com.example.astonlolapp.domain.use_cases.heroes.add_hero_as_favourite.AddHeroAsFavouriteUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.heroes.update_heroes.UpdateHeroesUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.read_onboarding.ReadOnboardingUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.save_onboarding.SaveOnboardingStateUseCase
import com.example.astonlolapp.domain.use_cases.signed_in.read_signedin.ReadSignedInUseCase
import com.example.astonlolapp.domain.use_cases.signed_in.save_signedin.SaveSignedInStateUseCase
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
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository),
            saveOnboardingStateUseCase = SaveOnboardingStateUseCase(repository = repository),
            readOnboardingUseCase = ReadOnboardingUseCase(repository = repository),
            getComicsFromCacheUseCase = GetComicsFromCacheUseCase(repository = repository),
            getSelectedComicsUseCase = GetSelectedComicsUseCase(repository = repository),
            getComicsFromApiUseCase = GetComicsFromApiUseCase(repository = repository),
            addHeroAsFavouriteUseCase = AddHeroAsFavouriteUseCase(repository = repository),
            updateHeroesUseCase = UpdateHeroesUseCase(repository = repository),
            saveSignedInStateUseCase = SaveSignedInStateUseCase(repository = repository),
            readSignedInUseCase = ReadSignedInUseCase(repository = repository)
        )
    }

}