package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.AuthRemoteSource
import com.example.timetracker.persistance.room.AuthLocalSource
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val authLocalSource: AuthLocalSource,
) {
    fun getUserId(): Observable<String> {
        return if(isOnline()){
             authRemoteSource.getUserId()
        } else {
            authLocalSource.getUserId()
        }
    }

    fun currentUser(): Observable<User?> {
        return if(isOnline()){
            authRemoteSource.getCurrentUser()
        } else
            authLocalSource.getCurrentUser()
    }

    fun isOnline(): Boolean {
        return authRemoteSource.getCurrentUser() != null
    }

    fun signOut(): Unit{
        return authRemoteSource.signOut()
    }
}