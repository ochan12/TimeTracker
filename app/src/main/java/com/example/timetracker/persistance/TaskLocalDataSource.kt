package com.example.timetracker.persistance

import android.util.Log
import com.example.timetracker.persistance.room.AppDatabase
import com.example.timetracker.persistance.room.RoomModule
import com.example.timetracker.persistance.room.TaskDao
import com.example.timetracker.task.Task
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskLocalDataSource @Inject constructor(
    db: AppDatabase
) {
    private var taskDao: TaskDao = db.taskDao()

      fun save(task: Task) {
          Log.e(null, "Saving")
         taskDao.insert(task.convertToRoom())
    }

}