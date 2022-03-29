package com.example.timetracker.space

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.timetracker.R
import com.example.timetracker.SaveTaskActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.persistance.TaskRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.*
import java.util.concurrent.Flow
import javax.inject.Inject

class SpaceActivity : Activity() {

    @Inject
    lateinit var space: Space

    @Inject
    lateinit var taskRepository: TaskRepository

    lateinit var startButton: Button
    lateinit var pauseButton: Button
    lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        setContentView(R.layout.activity_space)
        initializeElements()
    }

    private fun initializeElements() {
        findViewById<TextView?>(R.id.space_name).apply {
            text = space.getName()
        }
        startButton = findViewById(R.id.startTaskButton)
        stopButton = findViewById(R.id.stopTaskButton)
        pauseButton = findViewById(R.id.pauseTaskButton)
        checkTimerStatus()
        setClickListeners()
    }

    override fun onResume() {
        super.onResume()
        checkTimerStatus()
    }

    override fun onRestart() {
        super.onRestart()
        checkTimerStatus()
    }

    private fun checkTimerStatus() {
        if (space.getActiveTaskTimer()?.isTaskOngoing() == true) {
            startButton.visibility = View.INVISIBLE
            pauseButton.visibility = View.VISIBLE
        } else {
            startButton.visibility = View.VISIBLE
            pauseButton.visibility = View.INVISIBLE
        }
        stopButton.isEnabled = space.getActiveTaskTimer()?.hasTaskStarted() == true
    }

    private fun setClickListeners() {
        startButton.setOnClickListener {
            space.startTask()
            checkTimerStatus()
        }
        stopButton.setOnClickListener {
            space.stopTask()
            checkTimerStatus()



        }
        pauseButton.setOnClickListener {
            space.pauseTask()
            checkTimerStatus()
        }
    }
}