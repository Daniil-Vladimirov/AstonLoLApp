package com.example.astonlolapp.data.local

import androidx.room.TypeConverter
import com.example.astonlolapp.domain.model.Hero
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverter {

    var gson = Gson()
    private val separator = ","

    @TypeConverter
    fun convertListToString(listOfStrings: List<String>): String {
        val stringBuilder = StringBuffer()
        for (item in listOfStrings) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length + 1)
        return stringBuilder.toString()

    }

    @TypeConverter
    fun convertStingToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun heroToString(hero: Hero): String {
        return gson.toJson(hero)
    }

    @TypeConverter
    fun stringToHero(data: String): Hero {
        val listType = object : TypeToken<Hero>() {}.type
        return gson.fromJson(data, listType)
    }
}
