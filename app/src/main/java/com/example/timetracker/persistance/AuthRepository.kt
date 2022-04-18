package com.example.timetracker.persistance

import android.util.Log
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.remote.AuthRemoteSource
import com.example.timetracker.persistance.room.AuthLocalSource
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val authLocalSource: AuthLocalSource,
) : Taggable {

    fun getUserId(): Observable<String> {
        return Observable.create { user ->
            isOnline().subscribe { online ->
                if (online) authRemoteSource.getUserId().subscribe { user.onNext(it) }
                else authLocalSource.getUserId().subscribeOn(Schedulers.computation())
                    .subscribe { user.onNext(it) }
            }
        }
    }

    fun currentUser(): Observable<User> {
        Log.e(TAG, "currentUser")
        return Observable.create { user ->
            isOnline().subscribe { online ->
                if (online == true) authRemoteSource.getCurrentUser().subscribe { user.onNext(it) }
                else authLocalSource.getCurrentUser().subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .doOnNext { it -> user.onNext(it);Log.e(TAG, it.toString()) }
                    .doOnError { error -> Log.e(TAG, error.localizedMessage) }
                    .subscribe()

            }
        }
    }

    fun isOnline(): Observable<Boolean> {
        return Observable.create {
            authRemoteSource.getCurrentUser().subscribe({ user ->
                user?.equals(null)?.let { userOnline -> it.onNext(userOnline) }
            }, { _ -> it.onNext(false) })
        }
    }

    fun createUser(): Observable<Long> {
        return authLocalSource.createUser().toObservable()
    }

    fun signOut(): Unit {
        return authRemoteSource.signOut()
    }
}