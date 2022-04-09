package com.example.timetracker.persistance

import android.util.Log
import com.example.timetracker.persistance.remote.SpaceRemoteSource
import com.example.timetracker.space.Space
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SpaceRepository @Inject constructor(
    private val remoteSpaceRepository: SpaceRemoteSource,
    private val authRepository: AuthRepository
) {
    fun saveSpace(space: Space): Observable<String> {
        return remoteSpaceRepository.createSpace(space)
    }
    fun getSpaces(userId: String): Observable<List<Space>> {
        return remoteSpaceRepository.getSpaces(userId)
    }
    fun getSpaces(): Observable<List<Space>> {
        return remoteSpaceRepository.getSpaces(authRepository.getUserId())
    }
    fun getSpace(spaceId: String): Observable<Space> {
        return remoteSpaceRepository.getSpace(spaceId)
    }
}