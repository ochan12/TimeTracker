package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.AuthRemoteSource
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class AuthRepositoryModule {
    @Provides
    fun provideAuthRepository(authRemoteSource: AuthRemoteSource): AuthRepository {
        return AuthRepository(authRemoteSource)
    }
}