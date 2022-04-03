package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.AuthRemoteSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteSource: AuthRemoteSource
) {
    fun getUserId(): String {
        return authRemoteSource.getUserId()
    }

    fun currentUser(): Any? {
        return authRemoteSource.getCurrentUser()
    }
}