package com.example.astonlolapp.data.local

import androidx.room.TypeConverter

class DatabaseConverter {

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


}
