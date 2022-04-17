package com.example.timetracker.persistance

import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable

abstract class AuthSource {
     val collection = "users"
    abstract fun getUserId(): Observable<String>
    abstract fun getCurrentUser(): Observable<User?>
    abstract fun signOut(): Unit
}