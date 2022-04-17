package com.example.timetracker.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import com.example.timetracker.space.SpaceViewModel

class SaveTaskViewModelFactory(
    val taskDb: TaskRepository,
    val taskId: String,
    val spaceId: String,
    val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        SaveTaskViewModel(taskDb, taskId, spaceId, authRepository) as T
}