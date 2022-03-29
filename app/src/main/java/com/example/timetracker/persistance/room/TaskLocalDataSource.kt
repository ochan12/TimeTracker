package com.example.timetracker.persistance.room

import com.example.timetracker.persistance.room.AppDatabase
import com.example.timetracker.persistance.room.TaskDao
import com.example.timetracker.task.Task
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.withContext

@Singleton
class TaskLocalDataSource @Inject constructor(
    db: AppDatabase
) {
    private var taskDao: TaskDao = db.taskDao()

      suspend fun save(task: Task): Long {
         return withContext(Dispatchers.Default){
                    taskDao.insert(task.convertToRoom())
         }
    }

}