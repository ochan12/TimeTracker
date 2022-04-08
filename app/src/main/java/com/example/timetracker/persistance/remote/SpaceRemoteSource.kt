package com.example.timetracker.persistance.remote

import android.util.Log
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.SpaceSource
import com.example.timetracker.space.Space
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SpaceRemoteSource @Inject constructor(
    private val db: FirebaseFirestore
) : SpaceSource(), Taggable {

    override fun getSpace(spaceId: String): Observable<Space> {
        val ref = db.collection(this.collection).document(spaceId)
            .get()
        return Observable.create { emitter ->
            ref.addOnCompleteListener {
                val space = it.result.toObject(Space::class.java)
                space?.setId(it.result.id)
                Log.e(TAG, space.toString())
                emitter.onNext(space)
            }
        }

    }

    override fun getSpaces(userId: String): Observable<List<Space>> {
        val ref = db.collection(this.collection).whereEqualTo("userId", userId).get()
        return Observable.create { emitter ->
            ref.addOnCompleteListener {
                val space =
                emitter.onNext(it.result.map { doc ->
                    val space = doc.toObject(Space::class.java)
                    space.setId(doc.id)
                    space
                })
            }
        }
    }

    override fun createSpace(space: Space): Observable<String> {
        val ref = db.collection(this.collection).add(space)
        return Observable.create { emitter ->
            ref.addOnCompleteListener {
                emitter.onNext(it.result.id)
            }
        }
    }

}