package com.example.astonlolapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.astonlolapp.data.local.dao.HeroDao
import com.example.astonlolapp.data.local.dao.HeroRemoteKeyDao
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    companion object {
        fun createDatabase(context: Context, useInMemory: Boolean): HeroDatabase {
            val database = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(
                    context,
                    HeroDatabase::class.java
                )
            } else {
                Room.databaseBuilder(
                    context,
                    HeroDatabase::class.java,
                    "testDatabase"
                )
            }
            return database
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}