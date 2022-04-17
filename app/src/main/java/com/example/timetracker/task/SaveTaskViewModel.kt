package com.example.timetracker.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.TaskRepository
import javax.inject.Inject

class SaveTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskId: String,
    private val spaceId: String,
    private val authRepository: AuthRepository
) : ViewModel(), Taggable {
    private val _task: MutableLiveData<Task> by lazy {
        MutableLiveData<Task>().also {
            loadTask()
        }
    }

    val clicked = MutableLiveData(false)
    private val isSaving: MutableLiveData<Boolean> = MutableLiveData(false)

    fun isSaving() = isSaving
    fun getTask() = _task

    fun loadTask() {
        taskRepository.getTask(taskId).subscribe {
            _task.postValue(it)
        }
    }

    fun saveTask(description: String) {
        isSaving.postValue(true)
        val finalTask: Task = _task.value!!
        finalTask.setDescription(description)
        finalTask.setSpace(spaceId)
        authRepository.getUserId().subscribe { userId ->
            finalTask.setUserId(userId)
            taskRepository.saveTask(finalTask).subscribe {
                isSaving.postValue(false)
            }
        }
    }
}