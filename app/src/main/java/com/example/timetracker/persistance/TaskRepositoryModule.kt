package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.TaskRemoteSource
import dagger.Module
import dagger.Provides

@Module
class TaskRepositoryModule {
    @Provides
    fun provideTaskRepository(remoteTaskSource: TaskRemoteSource): TaskRepository {
        return TaskRepository(remoteTaskSource)
    }
}