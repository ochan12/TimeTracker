package com.example.timetracker.persistance.room

import android.util.Log
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthSource
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalSource @Inject constructor(
    private val db: AppDatabase
) : AuthSource(), Taggable {
    override fun getUserId(): Observable<String> {
        return Observable.create<String> {
            runBlocking {
                val user = db.userDao().getCurrentUser()
                user?.getId()?.let { it1 -> it.onNext(it1) }
            }
        }
    }

    override fun getCurrentUser(): Observable<User?> {
        return Observable.create<User> {
            runBlocking {
                val user = db.userDao().getCurrentUser()
                it.onNext(user)
            }
        }
    }

    override fun signOut() {
        Log.e(TAG, "user was never signed in")
    }
}