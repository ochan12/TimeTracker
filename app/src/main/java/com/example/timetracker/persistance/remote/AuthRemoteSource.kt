package com.example.timetracker.persistance.remote

import com.example.timetracker.persistance.AuthSource
import com.example.timetracker.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthSource() {

    override fun getUserId(): Observable<String> {
        return Observable.create {
            it.onNext(auth.currentUser?.uid!!)
        }


    }

    override fun getCurrentUser(): Observable<User?> {
        val userId = auth.currentUser?.uid
        return Observable.create { emitter ->
            if (userId != null) {
                db.collection(this.collection).document(userId).get().addOnCompleteListener {
                    emitter.onNext(it.result.toObject(User::class.java))
                }
            } else {
                emitter.onNext(null)
            }
        }
    }

    override fun signOut() {
        return auth.signOut()
    }
}