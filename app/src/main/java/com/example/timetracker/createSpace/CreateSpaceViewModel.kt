package com.example.timetracker.createSpace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.space.Space
import javax.inject.Inject

class CreateSpaceViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    private val currentSpace: Space = Space()
    private var isCreated: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changeName(name: String){
        currentSpace.setName(name)
    }

    fun getCreated() = isCreated

    fun createSpace(){
        if(currentSpace.getName() !== ""){
            authRepository.getUserId().let { currentSpace.setUserId(it) }
            spaceRepository.saveSpace(currentSpace).subscribe {
                isCreated.postValue(true)
            }
        }
    }
}