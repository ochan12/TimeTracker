package com.example.timetracker.di

import android.content.Context
import com.example.timetracker.MainActivity
import com.example.timetracker.auth.LoginActivity
import com.example.timetracker.auth.ProfileActivity
import com.example.timetracker.createSpace.CreateSpaceActivity
import com.example.timetracker.mainMenu.MainMenuActivity
import com.example.timetracker.persistance.AuthRepositoryModule
import com.example.timetracker.persistance.SpaceRepositoryModule
import com.example.timetracker.persistance.TaskRepositoryModule
import com.example.timetracker.persistance.remote.FirebaseModule
import com.example.timetracker.persistance.room.RoomModule
import com.example.timetracker.space.SpaceActivity
import com.example.timetracker.space.SpaceModule
import com.example.timetracker.task.ListTasksActivity
import com.example.timetracker.task.SaveTaskActivity
import com.example.timetracker.timer.TimerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TimerModule::class,
    SpaceModule::class,
    RoomModule::class,
    FirebaseModule::class,
    AuthRepositoryModule::class,
    SpaceRepositoryModule::class,
    TaskRepositoryModule::class
    ])
interface ApplicationGraph {
    fun inject(app: SpaceActivity)
    fun inject(app: SaveTaskActivity)
    fun inject(app: MainActivity)
    fun inject(app: MainMenuActivity)
    fun inject(app: CreateSpaceActivity)
    fun inject(app: ListTasksActivity)
    fun inject(app: ProfileActivity)
    fun inject(app: LoginActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationGraph
    }


}
