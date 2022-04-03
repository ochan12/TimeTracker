package com.example.timetracker.persistance

import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Observable
import java.util.*

abstract class TaskSource {
    val collection = "tasks"
    abstract fun getTask(id: String): Observable<Task?>
    abstract fun loadTasks(user: String): Observable<List<Task>>
    abstract fun loadTasks(): Observable<Task>
    abstract fun saveTask(task: Task): Observable<String>
    abstract fun convertToSourceTask(task: Task): Any
    abstract fun <T: HashMap<String, Any?>> convertToAppTask(task: T): Task

}