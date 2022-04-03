package com.example.timetracker.main_menu

import android.util.Log
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
    private val spaces: MutableLiveData<List<Space>?> = MutableLiveData(null)

    fun getSpaces() = spaces

    fun loadSpaces() {
        spaceRepository.getSpaces(authRepository.getUserId()).subscribe {
            spaces.postValue(it)
        }
    }
}