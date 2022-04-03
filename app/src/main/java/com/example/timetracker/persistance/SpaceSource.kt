package com.example.timetracker.persistance

import com.example.timetracker.space.Space
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

abstract class SpaceSource {
    val collection: String = "space"
    abstract fun getSpace(spaceId: String): Observable<Space?>
    abstract fun getSpaces(userId: String): Observable<List<Space>>
    abstract fun createSpace(space: Space): Observable<String>

}