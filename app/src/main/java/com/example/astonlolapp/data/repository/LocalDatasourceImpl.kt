package com.example.astonlolapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs
import com.example.astonlolapp.util.Constants
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImp(heroDatabase: HeroDatabase) : LocalDatasourceAbs {

    private val heroDao = heroDatabase.heroDao()
    private val comicsDao = heroDatabase.comicsDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

    override fun getComics(): Flow<PagingData<Comics>> {
        val pagerFactory = { comicsDao.getComics() }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ITEMS_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                pagerFactory()
            }
        ).flow
    }

    override suspend fun getSelectedComics(comicsId: Int): Comics {
        return comicsDao.getSelectedComics(comicsId = comicsId)
    }


    override suspend fun addHeroAsFavourite(hero: Hero) {
        heroDao.addHeroAsFavourite(hero = hero)
    }
}