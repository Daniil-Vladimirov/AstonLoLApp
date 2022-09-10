package com.example.astonlolapp.data.repository

import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs

class LocalDataSourceImp(dataBase: HeroDatabase) : LocalDatasourceAbs {

    private val heroDao = dataBase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }


}