package com.example.timetracker.task

import org.joda.time.DateTime

class TimeInterval constructor(
    private val startTime: DateTime?,
    private var endTime: DateTime?
) {

    fun setEndTime(endTime: DateTime?) = apply { this.endTime = endTime }

    fun getEndTime() = endTime
    fun getStartTime() = startTime

    override fun toString(): String {
        return "interval { startTime: $startTime, endTime: $endTime } "
    }

    fun isOngoing(): Boolean = hasStarted() && endTime === null

    fun hasStarted() = startTime !== null
}