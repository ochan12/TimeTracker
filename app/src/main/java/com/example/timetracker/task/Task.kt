package com.example.timetracker.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.firebase.firestore.Exclude
import org.joda.time.DateTime
import org.joda.time.Seconds
import javax.inject.Inject

@Entity(primaryKeys = ["id"])
class Task @Inject constructor() {

    @ColumnInfo(name = "description")
    private var description: String? = ""

    @ColumnInfo(name = "startTime")
    private var startTime: Long? = null

    @ColumnInfo(name = "endTime")
    private var endTime: Long? = null

    @ColumnInfo(name = "timeIntervals")
    private var timeIntervals: MutableList<TimeInterval> = ArrayList()

    @ColumnInfo(name = "space")
    private var space: String? = ""

    @ColumnInfo(name = "userId")
    private var userId: String? = ""

    @ColumnInfo(name = "id")
    private lateinit var id: String

    @ColumnInfo(name = "createdAt")
    private var createdAt: Long? = DateTime().millis

    @Ignore
    @Exclude
    private var isTaskOngoing: Boolean = false
        set(value) {
            field = value
        }

    fun stopTask() {
        endTimeInterval()
    }

    fun pauseTask() {
        endTimeInterval()
    }

    fun getDescription() = description
    fun getStartTime() = startTime
    fun getEndTime() = endTime
    fun getTimeIntervals() = timeIntervals
    fun getSpace() = space
    fun getUserId() = userId
    fun getCreatedAt() = createdAt

    fun setCreatedAt(createdAt: Long) {
        this.createdAt = createdAt
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setStartTime(startTime: Long) {
        this.startTime = startTime
    }

    fun setEndTime(endTime: Long) {
        this.endTime = endTime
    }

    fun setTimeIntervals(timeIntervals: MutableList<TimeInterval>) {
        this.timeIntervals = timeIntervals
    }

    fun setSpace(space: String) {
        this.space = space
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getId(): String? = this.id

    fun startTask() {
        val now = DateTime().millis
        endTime = null
        timeIntervals.add(
            TimeInterval(now, endTime)
        )
        if (startTime === null) {
            startTime = now
        }
    }

    private fun endTimeInterval() {
        val now = DateTime().millis
        endTime = now
        timeIntervals[timeIntervals.lastIndex].setEndTime(now)
    }

    fun isTaskOngoing(): Boolean {
        isTaskOngoing = this.startTime !== null && this.timeIntervals.last().isOngoing()
        return isTaskOngoing
    }

    fun hasTaskStarted() = this.startTime !== null && this.timeIntervals.last().hasStarted()

    override fun toString(): String =
        "desc: $description startTime: $startTime - endTime: $endTime - " +
                timeIntervals.toString()


    @Exclude
    private fun getTotalTime(): Long {
        var millis: Long = 0
        this.timeIntervals.forEach {
            millis = millis.plus(it.getTotalTime())
        }
        return millis
    }

    @Exclude
    fun getDuration(): String {
        val seconds = Seconds.seconds((getTotalTime() / 1000).toInt())
        val hours = seconds.toStandardHours().hours
        val minutes = seconds.toStandardMinutes().minutes
        val millis = getTotalTime()

        if (hours > 0) return "${hours}h ${minutes}m"
        if (minutes > 0) return "${minutes}m ${seconds.seconds}s"
        return "${seconds.seconds}s ${millis}ms"
    }

}