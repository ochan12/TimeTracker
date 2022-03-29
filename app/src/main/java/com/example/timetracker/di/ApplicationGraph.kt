package com.example.timetracker.di

import android.content.Context
import com.example.timetracker.MainActivity
import com.example.timetracker.persistance.remote.FirebaseModule
import com.example.timetracker.space.SpaceActivity
import com.example.timetracker.persistance.room.RoomModule
import com.example.timetracker.space.SpaceModule
import com.example.timetracker.timer.TimerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TimerModule::class, SpaceModule::class, RoomModule::class, FirebaseModule::class])
interface ApplicationGraph {
    fun inject(app: SpaceActivity)
    fun inject(app: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationGraph
    }


}
