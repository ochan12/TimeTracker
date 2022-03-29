package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.timetracker.task.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM RoomTask")
    fun getAll(): List<RoomTask>

    @Query("SELECT * FROM RoomTask ORDER BY createdAt LIMIT 1")
    fun getLast(): RoomTask

    @Query("SELECT * FROM RoomTask WHERE uid = :uid")
    fun getByUid(uid: Int): RoomTask?

    @Insert
    fun insert(room: RoomTask)

}