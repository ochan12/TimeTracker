package com.example.timetracker.persistance.remote

import android.util.Log
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthSource
import com.example.timetracker.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthSource(), Taggable {

    override fun getUserId(): Observable<String> {
        return Observable.create {
            if(auth.currentUser != null){
                it.onNext(auth.currentUser!!.uid)
            } else {
                it.onNext("")
            }
        }


    }

    override fun getCurrentUser(): Observable<User> {
        val userId = auth.currentUser?.uid
        Log.e(TAG, userId.toString())
        return Observable.create { emitter ->
            if (userId != null) {
                db.collection(this.collection).document(userId).get().addOnCompleteListener {
                    emitter.onNext(it.result.toObject(User::class.java))
                }
            } else {
                emitter.onNext(null)
                emitter.onComplete()
            }
        }
    }

    override fun signOut() {
        return auth.signOut()
    }

    override fun createUser(): Observable<Long> {
        return Observable.just(1)
    }
}