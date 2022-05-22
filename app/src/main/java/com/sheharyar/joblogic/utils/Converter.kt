package com.sheharyar.joblogic.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {
  /*  @TypeConverter
    fun fromGroupTaskMemberList(value: List<Comment>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Comment> {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(value, type)
    }*/

    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    /*@TypeConverter
    fun listToJson(value: List<MediaListModel>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<MediaListModel>::class.java).toList()*/
}