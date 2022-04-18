package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.timetracker.user.User
import io.reactivex.rxjava3.annotations.Nullable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.*

@Dao
interface UserDao {
    @Query("SELECT * FROM User LIMIT 1")
    fun getCurrentUser(): Single<Optional<User>>

    @Insert
    fun createUser(user: User): Completable
}