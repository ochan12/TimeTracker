package com.example.timetracker.createSpace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository

class CreateSpaceViewModelFactory(
    private val spaceRepository: SpaceRepository,
    private val authRepository: AuthRepository
) :ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        CreateSpaceViewModel(spaceRepository, authRepository) as T
}