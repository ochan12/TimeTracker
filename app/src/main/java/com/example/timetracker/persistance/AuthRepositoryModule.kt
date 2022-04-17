package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.AuthRemoteSource
import com.example.timetracker.persistance.room.AuthLocalSource
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class AuthRepositoryModule {
    @Provides
    fun provideAuthRepository(authRemoteSource: AuthRemoteSource, authLocalSource: AuthLocalSource): AuthRepository {
        return AuthRepository(authRemoteSource, authLocalSource)
    }
}