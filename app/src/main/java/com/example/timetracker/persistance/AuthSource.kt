package com.example.timetracker.persistance

interface AuthSource {
    fun getUserId(): String?
    fun getCurrentUser(): Any?
}