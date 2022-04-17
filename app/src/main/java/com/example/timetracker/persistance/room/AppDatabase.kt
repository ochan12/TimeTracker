package com.example.timetracker.persistance.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.timetracker.space.Space
import com.example.timetracker.task.Task
import com.example.timetracker.user.User

@Database(
    entities = [Task::class, User::class, Space::class], version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
    abstract fun spaceDao(): SpaceDao
}