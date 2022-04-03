package com.example.timetracker.user

class User {
    private var id: String = ""
    private var name: String = ""
    private var lastName: String = ""
    private var email: String = ""

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