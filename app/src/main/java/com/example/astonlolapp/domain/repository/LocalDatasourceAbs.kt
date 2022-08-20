package com.example.astonlolapp.domain.repository

import com.example.astonlolapp.domain.model.Hero

interface LocalDatasourceAbs {
    suspend fun getSelectedHero(heroId:Int):Hero
}