package com.example.astonlolapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.astonlolapp.data.local.dao.HeroDao
import com.example.astonlolapp.data.local.dao.HeroRemoteKeysDao
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeysDao

}