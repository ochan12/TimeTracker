package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Query
import com.example.timetracker.user.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM User LIMIT 1")
    fun getCurrentUser(): Maybe<User>
}