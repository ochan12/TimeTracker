package com.example.timetracker.helpers

interface Taggable {
    val TAG: String
        get() = this.javaClass.name
}