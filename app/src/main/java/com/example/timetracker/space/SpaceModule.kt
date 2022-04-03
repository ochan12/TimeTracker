package com.example.timetracker.space

import com.example.timetracker.timer.TaskTimer
import dagger.Module
import dagger.Provides

@Module
class SpaceModule {
    @Provides
    fun provideSpace(): Space {
        return Space()
    }
}