package com.example.timetracker.task

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.TaskRepository
import javax.inject.Inject

class SaveTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel(), Taggable {
    private val task: MutableLiveData<Task> = MutableLiveData(null)

    fun getTask() = task

    fun loadTask(taskId: String) {
        taskRepository.getTask(taskId).subscribe {
            task.postValue(it)
        }
    }
}