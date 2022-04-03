package com.example.timetracker.space

import com.example.timetracker.task.Task
import com.example.timetracker.timer.TaskTimer
import com.google.firebase.firestore.Exclude
import javax.inject.Inject

class Space @Inject constructor() {

    @Exclude
    private var activeTaskTimer: TaskTimer? = TaskTimer(Task())

    private var name: String = ""
    private var userId: String = ""


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

    @Exclude
    fun getActiveTaskTimer(): TaskTimer? = activeTaskTimer

    fun setName(name: String) {
        this.name = name
    }

    fun getUserId() = userId
    fun setUserId(userId: String) {
        this.userId = userId
    }

}