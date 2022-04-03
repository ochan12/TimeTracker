package com.example.timetracker.task

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.R
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import org.joda.time.DateTime
import javax.inject.Inject

class SaveTaskActivity @Inject constructor(): AppCompatActivity(), Taggable {

    @Inject
    lateinit var taskRepository: TaskRepository

    lateinit var startDateTextView: TextView
    lateinit var endDateTextView: TextView
    lateinit var taskDescription: TextView

    @Inject
    lateinit var spaceSource: SpaceRepository
    
    private val model: SaveTaskViewModel by viewModels {
        SaveTaskViewModelFactory(taskRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_task)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        Log.e(TAG, intent.getStringExtra("taskId").toString())
        initializeElements()
        intent.getStringExtra("taskId")?.let { model.loadTask(it) }
    }

    private fun initializeElements() {
        startDateTextView = findViewById(R.id.startDateText)
        endDateTextView = findViewById(R.id.endDateText)
        taskDescription = findViewById(R.id.taskDescription)
        model.getTask().observe(this) {
            if (it !== null) {
            startDateTextView.text = DateTime(it.getStartTime()).toLocalTime().toString()
            endDateTextView.text = DateTime(it.getEndTime()).toLocalTime().toString()
            taskDescription.text = it.getDescription()
            }
        }
    }
}