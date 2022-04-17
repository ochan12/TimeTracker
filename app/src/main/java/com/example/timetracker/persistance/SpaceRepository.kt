package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.SpaceRemoteSource
import com.example.timetracker.persistance.room.SpaceLocalSource
import com.example.timetracker.space.Space
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SpaceRepository @Inject constructor(
    private val remoteSpaceRepository: SpaceRemoteSource,
    private val spaceLocalSource: SpaceLocalSource,
    private val authRepository: AuthRepository
) {
    fun saveSpace(space: Space): Observable<String> {
        return Observable.create {
            authRepository.isOnline().subscribe { online ->
                if (online) remoteSpaceRepository.createSpace(space).subscribe { s -> it.onNext(s) }
                else spaceLocalSource.createSpace(space).subscribe { s -> it.onNext(s) }
            }
        }
    }

    fun getSpaces(userId: String): Observable<List<Space>> {
        return Observable.create {
            authRepository.isOnline().subscribe { online ->
                if (online) remoteSpaceRepository.getSpaces(userId).subscribe { s -> it.onNext(s) }
                else spaceLocalSource.getSpaces(userId).subscribe { s -> it.onNext(s) }
            }
        }
    }

    fun getSpace(spaceId: String): Observable<Space> {
        return Observable.create {
            authRepository.isOnline().subscribe { online ->
                if (online) remoteSpaceRepository.getSpace(spaceId).subscribe { s -> it.onNext(s) }
                else spaceLocalSource.getSpace(spaceId).subscribe { s -> it.onNext(s) }
            }
        }
    }
}