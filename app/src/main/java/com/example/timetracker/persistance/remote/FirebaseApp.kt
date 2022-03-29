package com.example.timetracker.persistance.remote

import com.example.timetracker.space.Space
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import javax.inject.Inject

class FirebaseApp @Inject constructor(
    val db: FirebaseFirestore
) {

    fun loadSpaces(user: String): Query {
        return db.collection(Collections().spaces).whereEqualTo("user", user)
    }
}