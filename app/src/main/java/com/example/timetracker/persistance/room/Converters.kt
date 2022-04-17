package com.example.timetracker.persistance.room

import androidx.room.TypeConverter
import com.example.timetracker.task.Task
import com.example.timetracker.task.TimeInterval
import com.google.gson.Gson
import org.joda.time.DateTime

class Converters {

    @TypeConverter
    fun listToJson(value: List<TimeInterval>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<TimeInterval>::class.java).toList()

}