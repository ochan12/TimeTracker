package com.example.timetracker.createSpace

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.space.Space
import org.joda.time.DateTime
import javax.inject.Inject

class CreateSpaceViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val currentSpace: Space = Space()
    var isCreated: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changeName(name: String) {
        currentSpace.setName(name)
    }


    fun createSpace() {
        if (currentSpace.getName() !== "") {
            currentSpace.setId(DateTime().millis.toString())
            authRepository.getUserId().subscribe { userId ->
                Log.e("userId", userId)
                if (!userId.isNullOrEmpty()) {
                    currentSpace.setUserId(userId)
                    spaceRepository.saveSpace(currentSpace).subscribe {
                        Log.e("isCreated", isCreated.hasActiveObservers().toString())
                        isCreated.postValue(true)
                    }
                }
            }
        }
    }
}