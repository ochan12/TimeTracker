package com.example.timetracker.user

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id"])
class User {
    @ColumnInfo(name = "id") private var id: String = ""
    @ColumnInfo(name = "name") private var name: String = ""
    @ColumnInfo(name = "lastName") private var lastName: String = ""
    @ColumnInfo(name = "email") private var email: String = ""

    fun getId() = id
    fun getName() = name
    fun getLastName() = lastName
    fun getEmail() = email

    fun setId(id: String) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun setEmail(email: String) {
        this.email = email
    }
}