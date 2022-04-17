package com.example.timetracker.persistance.room

import android.util.Log
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthSource
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalSource @Inject constructor(
    private val db: AppDatabase
) : AuthSource(), Taggable {
    override fun getUserId(): Observable<String?> {
        return Observable.create {
            db.userDao().getCurrentUser().subscribe { user ->
                it.onNext(user?.getId())
            }

        }
    }

    override fun getCurrentUser(): Observable<User?> {
        return Observable.create {
            db.userDao().getCurrentUser().subscribe { user ->
                it.onNext(user)
            }
        }
    }

    override fun signOut() {
        Log.e(TAG, "user was never signed in")
    }
}