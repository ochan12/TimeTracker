package com.example.timetracker.task

import com.example.timetracker.persistance.room.RoomTask
import com.google.firebase.firestore.Exclude
import org.joda.time.DateTime
import org.joda.time.Hours
import org.joda.time.Seconds
import javax.inject.Inject

class Task @Inject constructor() {
    private var description: String? = ""
    private var startTime: Long? = null
    private var endTime: Long? = null
    private var timeIntervals: MutableList<TimeInterval> = ArrayList()
    private var space: String? = ""
    private var userId: String? = ""
    private var id: String? = ""
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


    fun convertToRoom() = RoomTask(
        0,
        description,
        startTime = startTime!!,
        endTime = endTime!!,
        timeIntervals,
        createdAt = DateTime().toString()
    )
    @Exclude
    private fun getTotalTime(): Seconds {
        val seconds: Long = 0
        this.timeIntervals.forEach {
            seconds.plus(it.getTotalTime())
        }
        return Seconds.seconds((seconds / 1000).toInt())
    }

    @Exclude
    fun getDuration(): String {
        val minutes = getTotalTime().toStandardMinutes()
        val hours = getTotalTime().toStandardHours()
        val seconds = getTotalTime()
        if(hours.isGreaterThan(Hours.ZERO)) return "${hours.hours}h ${minutes.minutes}m"
        return "${minutes.minutes}m ${seconds.seconds}s"
    }

}