package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.SpaceRemoteSource
import com.example.timetracker.persistance.room.SpaceLocalSource
import dagger.Module
import dagger.Provides

@Module
class SpaceRepositoryModule {
    @Provides
    fun provideSpaceRepository(
        spaceRemoteSource: SpaceRemoteSource,
        spaceLocalSource: SpaceLocalSource,
        authRepository: AuthRepository
    ): SpaceRepository {
        return SpaceRepository(spaceRemoteSource, spaceLocalSource, authRepository)
    }
}