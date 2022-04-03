package com.example.timetracker.persistance

import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Observable

abstract class UserSource {
    val collection = "users"
    abstract fun getUser(userId: String): Observable<User?>
    abstract fun createUser(user: User): Observable<String>
}