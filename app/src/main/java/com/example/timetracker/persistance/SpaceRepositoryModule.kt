package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.SpaceRemoteSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class SpaceRepositoryModule {
    @Provides
    fun provideSpaceRepository(spaceRemoteSource: SpaceRemoteSource): SpaceRepository {
        return SpaceRepository(spaceRemoteSource)
    }
}