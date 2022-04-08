package com.example.timetracker.persistance

import com.example.timetracker.persistance.remote.TaskRemoteSource
import com.example.timetracker.persistance.room.TaskLocalDataSource
import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val remoteSource: TaskRemoteSource,
    private val authRepository: AuthRepository
) {
    fun saveTask(task: Task): Observable<String> {
        task.setUserId(authRepository.getUserId())
        return remoteSource.saveTask(task)
    }

    fun getTask(taskId: String): Observable<Task?> {
        return remoteSource.getTask(taskId)
    }

    fun getAllTasks(): Observable<List<Task>> {
        return remoteSource.loadTasks(authRepository.getUserId())
    }
}