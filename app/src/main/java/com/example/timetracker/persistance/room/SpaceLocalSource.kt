package com.example.timetracker.persistance.room

import com.example.timetracker.persistance.SpaceSource
import com.example.timetracker.space.Space
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SpaceLocalSource @Inject constructor(
    private val db: AppDatabase
): SpaceSource() {
    override fun getSpace(spaceId: String): Observable<Space> {
        return db.spaceDao().getSpace(spaceId).toObservable()
    }

    override fun getSpaces(userId: String): Observable<List<Space>> {
        return db.spaceDao().getSpaces(userId).toObservable()
    }

    override fun createSpace(space: Space): Observable<String> {
        return db.spaceDao().createSpace(space).toObservable()
    }
}