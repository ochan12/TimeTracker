package com.example.timetracker.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import com.example.timetracker.space.Space
import javax.inject.Inject

class ListTasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val spaceRepository: SpaceRepository,
) : ViewModel() {

    private val spaces: MutableLiveData<List<Space>> by lazy {
        MutableLiveData<List<Space>>().also {
            loadSpaces()
        }
    }

    fun getUserSpaces() = spaces

    private val tasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData<List<Task>>().also {
            loadTasks()
        }
    }

    private fun loadSpaces() {
        spaceRepository.getSpaces().subscribe {
            spaces.postValue(it)
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