package com.example.timetracker.space

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.example.timetracker.task.Task
import com.example.timetracker.timer.TaskTimer
import com.google.firebase.firestore.Exclude
import javax.inject.Inject

@Entity(primaryKeys = ["id"])
class Space @Inject constructor() {

    @Ignore
    @Exclude
    private var activeTaskTimer: TaskTimer? = TaskTimer(Task())

    @ColumnInfo(name = "name") private var name: String = ""
    @ColumnInfo(name = "userId") private var userId: String = ""
    @ColumnInfo(name = "id") private var id: String = ""

    fun getId() = id
    fun setId(id: String) {
        this.id = id
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

    @Ignore
    @Exclude
    fun getActiveTaskTimer(): TaskTimer? = activeTaskTimer

    fun setName(name: String) {
        this.name = name
    }

    fun getUserId() = userId
    fun setUserId(userId: String) {
        this.userId = userId
    }

    override fun toString(): String {
        return "Space(activeTaskTimer=$activeTaskTimer, name='$name', userId='$userId', id='$id')"
    }


}