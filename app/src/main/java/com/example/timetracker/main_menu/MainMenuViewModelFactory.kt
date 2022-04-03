package com.example.timetracker.main_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.task.SaveTaskViewModel
import com.google.firebase.auth.FirebaseAuth

class MainMenuViewModelFactory(
    private val spaceRepository: SpaceRepository,
    private val auth: AuthRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MainMenuViewModel(spaceRepository, auth) as T
}