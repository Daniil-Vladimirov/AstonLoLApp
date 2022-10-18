package com.example.astonlolapp.domain.use_cases

import com.example.astonlolapp.domain.use_cases.comics.get_comics.GetComicsFromApiUseCase
import com.example.astonlolapp.domain.use_cases.comics.get_comics.GetComicsFromCacheUseCase
import com.example.astonlolapp.domain.use_cases.comics.get_selected_comics.GetSelectedComicsUseCase
import com.example.astonlolapp.domain.use_cases.heroes.add_hero_as_favourite.AddHeroAsFavouriteUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.heroes.update_heroes.UpdateHeroesUseCase
import com.example.astonlolapp.domain.use_cases.onboarding_state.read_onboarding.ReadOnboardingUseCase
import com.example.astonlolapp.domain.use_cases.onboarding_state.save_onboarding.SaveOnboardingStateUseCase
import com.example.astonlolapp.domain.use_cases.signed_in_state.read_signedin.ReadSignedInUseCase
import com.example.astonlolapp.domain.use_cases.signed_in_state.save_signedin.SaveSignedInStateUseCase
import com.example.astonlolapp.domain.use_cases.user.delete_user.DeleteUserUseCase
import com.example.astonlolapp.domain.use_cases.user.get_user.GetUserUseCase
import com.example.astonlolapp.domain.use_cases.user.sign_out.SignOutUseCase
import com.example.astonlolapp.domain.use_cases.user.token_verification.TokeVerificationUseCase
import com.example.astonlolapp.domain.use_cases.user.update_user.UpdateUserUseCase

data class UseCases(

    //Hero operations
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val addHeroAsFavouriteUseCase: AddHeroAsFavouriteUseCase,
    val updateHeroesUseCase: UpdateHeroesUseCase,

    //Comics operations
    val getSelectedComicsUseCase: GetSelectedComicsUseCase,
    val getComicsFromApiUseCase: GetComicsFromApiUseCase,
    val getComicsFromCacheUseCase: GetComicsFromCacheUseCase,

    //State operations
    val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase,
    val saveSignedInStateUseCase: SaveSignedInStateUseCase,
    val readSignedInUseCase: ReadSignedInUseCase,

    //User operations
    val deleteUserUseCase: DeleteUserUseCase,
    val updateInfo: UpdateUserUseCase,
    val signOutUseCase: SignOutUseCase,
    val getUseCase: GetUserUseCase,
    val tokenVerificationUseCase: TokeVerificationUseCase
)
