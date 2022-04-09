package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.SpaceRemoteSource
import dagger.Module
import dagger.Provides

@Module
class SpaceRepositoryModule {
    @Provides
    fun provideSpaceRepository(
        spaceRemoteSource: SpaceRemoteSource,
        authRepository: AuthRepository
    ): SpaceRepository {
        return SpaceRepository(spaceRemoteSource, authRepository)
    }
}