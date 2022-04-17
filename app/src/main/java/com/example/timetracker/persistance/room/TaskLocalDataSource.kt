package com.example.timetracker.persistance.room

import com.example.timetracker.persistance.TaskSource
import com.example.timetracker.persistance.room.AppDatabase
import com.example.timetracker.persistance.room.TaskDao
import com.example.timetracker.task.Task
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.withContext
import java.util.HashMap

@Singleton
class TaskLocalDataSource @Inject constructor(
    db: AppDatabase
): TaskSource() {
    private var taskDao: TaskDao = db.taskDao()
    override fun getTask(id: String): Observable<Task?> {
        return taskDao.getByUid(id).toObservable()
    }

    override fun loadTasks(user: String): Observable<List<Task>> {
        return taskDao.getByUserId(user).toObservable()
    }

    override fun loadTasks(): Observable<Task> {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task): Observable<String> {
        return taskDao.insert(task).toObservable()
    }

    override fun convertToSourceTask(task: Task): Any {
        TODO("Not yet implemented")
    }

    override fun <T : HashMap<String, Any?>> convertToAppTask(task: T): Task {
        TODO("Not yet implemented")
    }


}