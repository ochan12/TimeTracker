package com.example.timetracker.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import com.example.timetracker.space.SpaceViewModel

class SaveTaskViewModelFactory(
    val taskDb: TaskRepository,
    val taskId: String,
    val spaceId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        SaveTaskViewModel(taskDb, taskId, spaceId) as T
}