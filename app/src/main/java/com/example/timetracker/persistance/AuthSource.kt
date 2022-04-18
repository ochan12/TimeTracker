package com.example.timetracker.persistance

import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import java.util.*

abstract class AuthSource {
    val collection = "users"
    val OFFLINE_USER = "OFFLINE_USER"
    abstract fun getUserId(): Observable<String>
    abstract fun getCurrentUser(): Observable<User>
    abstract fun signOut(): Unit
    abstract fun createUser(): Completable
}