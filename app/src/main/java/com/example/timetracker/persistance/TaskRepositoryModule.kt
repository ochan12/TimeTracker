package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.TaskRemoteSource
import dagger.Module
import dagger.Provides

@Module
class TaskRepositoryModule {
    @Provides
    fun provideTaskRepository(remoteTaskSource: TaskRemoteSource, authRepository: AuthRepository): TaskRepository {
        return TaskRepository(remoteTaskSource, authRepository)
    }
}