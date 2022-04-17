package com.example.timetracker.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import com.example.timetracker.space.Space
import javax.inject.Inject

class ListTasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val spaceRepository: SpaceRepository,
    private val authRepository: AuthRepository
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
        authRepository.getUserId().subscribe { userId ->
            spaceRepository.getSpaces(userId).subscribe {
                spaces.postValue(it)
            }
        }
    }

    @JvmName("getTasks1")
    fun getTasks() = tasks

    private fun loadTasks() {
        authRepository.getUserId().subscribe { userId ->
            taskRepository.getAllTasks(userId).subscribe {
                tasks.postValue(it)
            }
        }
    }
}