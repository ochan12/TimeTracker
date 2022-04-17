package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Query
import com.example.timetracker.user.User

@Dao
interface UserDao {
    @Query("SELECT * FROM RoomUser LIMIT 1")
    fun getCurrentUser(): User?
}