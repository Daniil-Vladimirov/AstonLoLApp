package com.example.astonlolapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.model.HeroRemoteKeys
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator(
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase
) : RemoteMediator<Int, Hero>() {

    private val heroDao = heroDatabase.heroDao()
    private val remoteKeyDao = heroDatabase.heroRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val maxTimeout = 1440
        val currentTime = System.currentTimeMillis()
        val lastUpdate = remoteKeyDao.getRemoteKeys(1)?.lastUpdated ?: 0L
        val timePassed = (currentTime - lastUpdate) / 1000 / 60
        return if (timePassed <= maxTimeout) {
            Timber.d("Skip Initial Refresh")
            InitializeAction.SKIP_INITIAL_REFRESH

        } else {
            Timber.d("Initial Refresh")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Hero>
    ): MediatorResult {

        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                        Timber.d("REFRESH")
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1

                }
                LoadType.PREPEND -> {
                    Timber.d("PREPEND")
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage


                }
                LoadType.APPEND -> {
                    Timber.d("APPEND")
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextPage

                }
            }

            val response = heroApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                Timber.tag("RemoteMediator").d("heroes are not empty")
                heroDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        remoteKeyDao.deleteAllRemoteKeys()

                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val lastUpdate = response.lastUpdated

                    val remoteKeys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = lastUpdate
                        )
                    }
                    remoteKeyDao.addAllRemoteKeys(heroRemoteKeys = remoteKeys)
                    heroDao.addHeroes(heroes = response.heroes)


                }

            }
            MediatorResult.Success(
                endOfPaginationReached = response.nextPage == null
            )

        } catch (exception: IOException) {
            Timber.d(exception)
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            Timber.d(exception)
            return MediatorResult.Error(exception)
        }
        catch (exception: Exception) {
            Timber.d(exception)
            return MediatorResult.Error(exception)
        }

    }


    private suspend fun getRemoteKeysClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        val firstPage = state.pages.firstOrNull()
        val firstRemoteKey =
            if (firstPage?.data?.isNotEmpty() == true) {
                firstPage.data.firstOrNull()?.let { hero ->
                    remoteKeyDao.getRemoteKeys(heroId = hero.id)
                }
            } else null
        return firstRemoteKey
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { train ->
            remoteKeyDao.getRemoteKeys(heroId = train.id)
        }
    }

    private fun parseMillis(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }
}