package com.example.timetracker.persistance

import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource
) {
      fun saveTask(task: Task) {
          Thread {
              Runnable {
                  taskLocalDataSource.save(task)
              }.run()
          }.start()
    }
}