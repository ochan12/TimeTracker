package com.example.timetracker.timer

import com.example.timetracker.task.Task
import dagger.Module
import dagger.Provides

@Module
class TimerModule {

    @Provides
    fun provideTimer(): TaskTimer {
        return TaskTimer(Task())
    }
}