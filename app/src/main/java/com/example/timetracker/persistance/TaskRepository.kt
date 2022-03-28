package com.example.timetracker.persistance

import com.example.timetracker.task.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource
) {
      fun saveTask(task: Task) {
        taskLocalDataSource.save(task)
    }
}