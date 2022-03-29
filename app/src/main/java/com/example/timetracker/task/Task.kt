package com.example.timetracker.task

import com.example.timetracker.persistance.room.RoomTask
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.core.SingleSource
import org.joda.time.DateTime
import javax.inject.Inject

class Task @Inject constructor() {
    private var description: String? = ""
    private var startTime: DateTime? = null
    private var endTime: DateTime? = null
    private var timeIntervals: MutableList<TimeInterval> = ArrayList()

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

    fun startTask() {
        val now = DateTime()
        endTime = null
        timeIntervals.add(
            TimeInterval(now, endTime)
        )
        if (startTime === null) {
            startTime = now
        }
    }

    private fun endTimeInterval() {
        val now = DateTime()
        endTime = now
        timeIntervals[timeIntervals.lastIndex].setEndTime(now)
    }

    fun isTaskOngoing() =
        this.startTime !== null && this.timeIntervals.last().isOngoing()

    fun hasTaskStarted() = this.startTime !== null && this.timeIntervals.last().hasStarted()

    override fun toString(): String =
        "desc: $description startTime: $startTime - endTime: $endTime - " +
                timeIntervals.toString()


    fun convertToRoom() = RoomTask(
        0,
        description,
        startTime = startTime!!.millis,
        endTime = endTime!!.millis,
        timeIntervals,
        createdAt = DateTime().toString()
    )


}