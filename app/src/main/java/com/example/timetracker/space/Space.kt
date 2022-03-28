package com.example.timetracker.space

import com.example.timetracker.task.Task
import com.example.timetracker.timer.TaskTimer
import javax.inject.Inject

class Space @Inject constructor(
    private val name: String,
    private var activeTaskTimer: TaskTimer? = null
) {
    init {
        if (activeTaskTimer == null) {
            activeTaskTimer = TaskTimer(Task())
        }
    }

    fun pauseTask() {
        activeTaskTimer?.pauseTask()
    }

    fun startTask() {
        activeTaskTimer?.startTask()
    }

    fun stopTask() {
        activeTaskTimer?.stopTask()
    }

    fun getName() = name
    fun getActiveTaskTimer(): TaskTimer? = activeTaskTimer

}