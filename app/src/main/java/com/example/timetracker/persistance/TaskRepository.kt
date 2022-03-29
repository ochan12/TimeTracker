package com.example.timetracker.persistance

import com.example.timetracker.persistance.room.TaskLocalDataSource
import com.example.timetracker.task.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource
) {
      suspend fun saveTask(task: Task): Long {
          return taskLocalDataSource.save(task)
    }
}