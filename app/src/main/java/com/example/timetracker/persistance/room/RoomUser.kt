package com.example.timetracker.persistance.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id"])
data class RoomUser(
    @ColumnInfo(name = "id", defaultValue = "localUser") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "email") var email: String
)