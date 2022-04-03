package com.example.timetracker.persistance.remote

import com.example.timetracker.persistance.SpaceSource
import com.example.timetracker.space.Space
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SpaceRemoteSource @Inject constructor(
    private val db: FirebaseFirestore
) : SpaceSource() {

    override fun getSpace(spaceId: String): Single<Space> {
        return Single.just(
            db.collection(this.collection).whereEqualTo("id", spaceId)
                .get().result?.documents?.get(0)
                ?.toObject(Space::class.java)!!
        )
    }

    override fun getSpaces(userId: String): Observable<List<Space>> {
        return Observable.fromArray(
            db.collection(this.collection).whereEqualTo("userId", userId)
                .get().result?.toObjects(Space::class.java)
        )
    }

    override fun createSpace(space: Space): Space? {
        return db.collection(this.collection)
            .add(space).result?.get()?.result?.toObject(Space::class.java)
    }

}