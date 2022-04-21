package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.timetracker.user.User
import io.reactivex.rxjava3.annotations.Nullable
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.core.Observable
import java.util.*

@Dao
interface UserDao {
    @Query("SELECT * FROM User LIMIT 1")
    fun getCurrentUser(): Maybe<User>

    @Insert
    fun createUser(user: User): Completable
}