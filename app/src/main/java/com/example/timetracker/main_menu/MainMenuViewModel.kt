package com.example.timetracker.main_menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.space.Space
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val spaces: MutableLiveData<List<Space>?> by lazy {
        MutableLiveData<List<Space>?>().also {
            loadSpaces()
        }
    }

    @JvmName("getSpaces1")
    fun getSpaces() = spaces

    fun loadSpaces() {
        authRepository.getUserId().subscribe { userId ->
            spaceRepository.getSpaces(userId).subscribe {
                spaces.postValue(it)
            }
        }
    }
}