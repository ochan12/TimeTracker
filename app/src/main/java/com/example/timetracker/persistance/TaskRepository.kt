package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.TaskRemoteSource
import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val remoteSource: TaskRemoteSource
) {
    fun saveTask(task: Task): Observable<String> {
        return remoteSource.saveTask(task)

    }

    fun getTask(taskId: String): Observable<Task?> {
        return remoteSource.getTask(taskId)
    }

    fun getAllTasks(userId: String): Observable<List<Task>> {
        return remoteSource.loadTasks(userId)
    }
}