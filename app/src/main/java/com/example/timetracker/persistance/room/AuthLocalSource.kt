package com.example.timetracker.persistance.room

import android.util.Log
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthSource
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalSource @Inject constructor(
    private val db: AppDatabase
) : AuthSource(), Taggable {
    override fun getUserId(): Observable<String> {
        return Observable.create {
            db.userDao().getCurrentUser().subscribe { user ->
                if(user?.get() != null)
                it.onNext(user.get().getId())
            }

        }
    }

    override fun getCurrentUser(): Observable<User> {
        Log.e(TAG, "getCurrentUser")
        return Observable.create {
            db.userDao().getCurrentUser().subscribe { user ->
                if(user.isPresent){
                    it.onNext(user.get())
                }
            }
        }
    }

    override fun signOut() {
        Log.e(TAG, "user was never signed in")
    }

    override fun createUser(): Completable {
        val newUser = User()
        newUser.setEmail(OFFLINE_USER)
        newUser.setId(OFFLINE_USER)
        newUser.setName("")
        newUser.setLastName("")
        return db.userDao().createUser(newUser)
    }
}