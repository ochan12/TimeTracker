package com.example.timetracker.persistance.room

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideRoomModule(applicationContext: Context): AppDatabase {
        // TODO: Remove allow main threads
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "local-db").allowMainThreadQueries().build()
    }
}