package com.example.timetracker.mainMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository

class MainMenuViewModelFactory(
    private val spaceRepository: SpaceRepository,
    private val auth: AuthRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MainMenuViewModel(spaceRepository, auth) as T
}