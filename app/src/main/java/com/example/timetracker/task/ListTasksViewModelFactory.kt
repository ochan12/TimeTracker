package com.example.timetracker.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository

class ListTasksViewModelFactory (
    private val taskDb: TaskRepository,
    private val spaceRepository: SpaceRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            ListTasksViewModel(taskDb, spaceRepository) as T
}