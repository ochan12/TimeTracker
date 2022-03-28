package com.example.timetracker.timer

import com.example.timetracker.task.Task
import com.example.timetracker.task.TimeInterval
import org.joda.time.DateTime
import javax.inject.Inject

class TaskTimer @Inject constructor(var currentTask: Task?) {

    fun isTaskOngoing() = currentTask?.isTaskOngoing()
    fun hasTaskStarted() = currentTask?.hasTaskStarted()

    fun stopTask() {
        currentTask?.stopTask()
    }

    fun pauseTask() {
        currentTask?.pauseTask()
    }

    fun startTask() {
        currentTask?.startTask()
    }
}