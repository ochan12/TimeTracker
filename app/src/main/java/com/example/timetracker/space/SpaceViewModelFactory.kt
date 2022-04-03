package com.example.timetracker.space

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository

class SpaceViewModelFactory(
    val taskDb: TaskRepository,
    val db: SpaceRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        SpaceViewModel(taskDb, db) as T
}