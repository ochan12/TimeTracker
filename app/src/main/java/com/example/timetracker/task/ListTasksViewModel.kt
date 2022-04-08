package com.example.timetracker.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.TaskRepository
import javax.inject.Inject

class ListTasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val tasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData<List<Task>>().also {
            loadTasks()
        }
    }

    @JvmName("getTasks1")
    fun getTasks() = tasks

    private fun loadTasks() {
        taskRepository.getAllTasks().subscribe {
            tasks.postValue(it)
        }
    }
}