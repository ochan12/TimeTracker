package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.AuthRemoteSource
import com.example.timetracker.persistance.room.AuthLocalSource
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val authLocalSource: AuthLocalSource,
) {

    fun getUserId(): Observable<String?> {
        return Observable.create { user ->
            isOnline().subscribe { online ->
                if (online) authRemoteSource.getUserId().subscribe { user.onNext(it) }
                else authLocalSource.getUserId().subscribeOn(Schedulers.computation())
                    .subscribe { user.onNext(it) }
            }
        }
    }

    fun currentUser(): Observable<User?> {
        return Observable.create { user ->
            isOnline().subscribe { online ->
                if (online) authRemoteSource.getCurrentUser().subscribe { user.onNext(it) }
                else authLocalSource.getCurrentUser().subscribeOn(Schedulers.computation())
                    .subscribe { user.onNext(it) }
            }
        }
    }

    fun isOnline(): Observable<Boolean> {
        return Observable.create {
            authRemoteSource.getCurrentUser().subscribe({ user ->
                user?.equals(null)?.let { userOnline -> it.onNext(userOnline) }
            }, { error -> it.onNext(false) })
        }
    }

    fun signOut(): Unit {
        return authRemoteSource.signOut()
    }
}