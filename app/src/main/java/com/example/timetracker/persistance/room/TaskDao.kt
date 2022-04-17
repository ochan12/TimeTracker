package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): Flowable<List<Task>>

    @Query("SELECT * FROM Task ORDER BY createdAt LIMIT 1")
    fun getLast(): Maybe<Task>

    @Query("SELECT * FROM Task WHERE id = :uid")
    fun getByUid(uid: String): Maybe<Task>

    @Query("SELECT * FROM Task WHERE userId = :uid")
    fun getByUserId(uid: String): Flowable<List<Task>>

    @Insert
    fun insert(room: Task): Completable

}