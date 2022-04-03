package com.example.timetracker.auth

interface AuthSource {
    fun loginWithGoogle()
    fun loginWithEmail()
}