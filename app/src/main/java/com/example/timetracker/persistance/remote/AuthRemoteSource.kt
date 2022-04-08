package com.example.timetracker.persistance.remote

import com.example.timetracker.persistance.AuthSource
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRemoteSource @Inject constructor(
    private val auth: FirebaseAuth
) : AuthSource {

    override fun getUserId(): String {
        return auth.currentUser?.uid!!
    }

    override fun getCurrentUser(): Any? {
        return auth.currentUser
    }

    override fun signOut() {
        return auth.signOut()
    }
}