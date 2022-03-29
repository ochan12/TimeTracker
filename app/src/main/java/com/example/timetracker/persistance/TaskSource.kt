package com.example.timetracker.persistance

import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface TaskSource {
    fun getTask(id: String): Single<Task>
    fun loadTasks(user: String): Observable<List<Task>>
    fun loadTasks(): Observable<Task>
    fun saveTask(task: Task): String
}