package com.example.timetracker.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.timetracker.space.Space
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface SpaceDao {
    @Query("SELECT * FROM Space WHERE id = :spaceId")
    fun getSpace(spaceId: String): Single<Space>

    @Query("SELECT * FROM Space WHERE userId = :userId")
    fun getSpaces(userId: String): Flowable<List<Space>>

    @Insert
    fun createSpace(space: Space): Completable
}