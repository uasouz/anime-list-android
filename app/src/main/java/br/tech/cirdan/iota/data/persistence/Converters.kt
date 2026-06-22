package br.tech.cirdan.iota.data.persistence

import androidx.room.TypeConverter
import br.tech.cirdan.iota.data.model.Genre
import br.tech.cirdan.iota.data.model.Studio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreList(value: List<Genre>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStudioList(value: List<Studio>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStudioList(value: String): List<Studio>? {
        val listType = object : TypeToken<List<Studio>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
}