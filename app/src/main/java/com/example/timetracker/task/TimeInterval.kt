package com.example.timetracker.task

import org.joda.time.DateTime
import org.joda.time.Seconds

class TimeInterval constructor(
    private var startTime: Long?,
    private var endTime: Long?
) {

    private var ongoing = false

    constructor() : this(null, null) {
        startTime = null
        endTime = null
    }

    fun setStartTime(startTime: Long) {
        this.startTime = startTime
    }

    fun setEndTime(endTime: Long) {
         this.endTime = endTime
    }

    fun getEndTime() = endTime
    fun getStartTime() = startTime

    override fun toString(): String {
        return "interval { startTime: $startTime, endTime: $endTime } "
    }

    fun isOngoing(): Boolean {
        ongoing = hasStarted() && endTime === null
        return ongoing
    }

    fun hasStarted() = startTime !== null

    fun getTotalTime(): Long {
        return DateTime(endTime).millis - DateTime(startTime).millis
    }
}