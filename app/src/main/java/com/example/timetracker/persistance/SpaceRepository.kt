package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.SpaceRemoteSource
import com.example.timetracker.space.Space
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SpaceRepository @Inject constructor(
    private val remoteSpaceRepository: SpaceRemoteSource
) {
    fun saveSpace(space: Space) {
        remoteSpaceRepository.createSpace(space)
    }
    fun getSpaces(userId: String): Observable<List<Space>> {
        return remoteSpaceRepository.getSpaces(userId)
    }
}