package com.alexberghii.core.database.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi


class StringConverters {

    private val moshiAdapter = Moshi.Builder().build().adapter(List::class.java)

    @TypeConverter
    fun stringListToJson(list: List<String>) = moshiAdapter.toJson(list)

    @Suppress("UNCHECKED_CAST")
    @TypeConverter
    fun jsonToStringList(json: String): List<String> = moshiAdapter.fromJson(json) as List<String>
}