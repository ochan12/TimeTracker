package com.example.timetracker.persistance.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.timetracker.task.TimeInterval

@Entity
data class RoomTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid") var uid: Int,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "startTime") var startTime: Long,
    @ColumnInfo(name = "endTime") var endTime: Long,
    @ColumnInfo(name = "timeIntervals") var timeInterval: MutableList<TimeInterval>,
    @ColumnInfo(name = "createdAt") var createdAt: String,
)
