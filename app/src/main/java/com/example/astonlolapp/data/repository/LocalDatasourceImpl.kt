package com.example.astonlolapp.data.repository

import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs

class LocalDataSourceImp(dataBase: HeroDatabase) : LocalDatasourceAbs {

    private val heroDao = dataBase.heroDao()
    private val comicsDao = dataBase.comicsDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

    override suspend fun getComics(): List<Comics> {
        return comicsDao.getComics()
    }
}