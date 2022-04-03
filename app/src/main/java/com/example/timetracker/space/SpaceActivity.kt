package com.example.timetracker.space

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.R
import com.example.timetracker.task.SaveTaskActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import javax.inject.Inject

class SpaceActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var taskRepository: TaskRepository

    @Inject
    lateinit var spaceSource: SpaceRepository

    lateinit var startButton: Button
    lateinit var pauseButton: Button
    lateinit var stopButton: Button


    private val model: SpaceViewModel by viewModels {
        SpaceViewModelFactory(taskRepository, spaceSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        setContentView(R.layout.activity_space)
        initializeElements()
    }

    private fun initializeElements() {
        model.getSpace().observe(
            this
        ) {
            findViewById<TextView?>(R.id.space_name).apply { text = it.getName() }
            checkTimerStatus(it)
        }

        model.getSavedTaskId().observe(this) {
            if (it !== null) {
                val intent = Intent(this, SaveTaskActivity::class.java)
                intent.putExtra("taskId", it)
                startActivity(intent)
            }
        }

        startButton = findViewById(R.id.startTaskButton)
        stopButton = findViewById(R.id.stopTaskButton)
        pauseButton = findViewById(R.id.pauseTaskButton)
        setClickListeners()
    }

    private fun checkTimerStatus(space: Space?) {
        if (space?.getActiveTaskTimer()?.isTaskOngoing() == true) {
            startButton.visibility = View.INVISIBLE
            pauseButton.visibility = View.VISIBLE
        } else {
            startButton.visibility = View.VISIBLE
            pauseButton.visibility = View.INVISIBLE
        }
        stopButton.isEnabled = space?.getActiveTaskTimer()?.hasTaskStarted() == true
    }

    private fun setClickListeners() {
        startButton.setOnClickListener {
            model.getSpace().value?.startTask()
            checkTimerStatus(model.getSpace().value)
        }
        stopButton.setOnClickListener {
            model.getSpace().value?.stopTask()
            model.saveTask()
            finish()

        }
        pauseButton.setOnClickListener {
            model.getSpace().value?.pauseTask()
            checkTimerStatus(model.getSpace().value)
        }
    }
}