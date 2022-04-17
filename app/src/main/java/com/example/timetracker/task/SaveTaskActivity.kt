package com.example.timetracker.task

import android.app.AuthenticationRequiredException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.R
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import com.example.timetracker.space.SpaceActivity
import org.joda.time.DateTime
import javax.inject.Inject

class SaveTaskActivity @Inject constructor() : AppCompatActivity(), Taggable {

    @Inject
    lateinit var taskRepository: TaskRepository

    lateinit var startDateTextView: TextView
    lateinit var endDateTextView: TextView
    lateinit var taskDescription: TextView
    lateinit var saveButton: Button

    @Inject
    lateinit var spaceSource: SpaceRepository
    @Inject
    lateinit var authRepository: AuthRepository

    private val model: SaveTaskViewModel by viewModels {
        SaveTaskViewModelFactory(
            taskRepository,
            intent.getStringExtra("taskId")!!,
            intent.getStringExtra("spaceId")!!,
            authRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_task)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        initializeElements()
        intent.getStringExtra("taskId")?.let { model.loadTask() }
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

        model.isSaving().observe(this) {
            if (it == false && model.clicked.value == true) {
                val newIntent = Intent(this, SpaceActivity::class.java)
                intent.getStringExtra("spaceId")?.let { newIntent.putExtra("spaceId", it) }
                startActivity(newIntent)
                finish()
            }
        }

        saveButton = findViewById(R.id.saveTaskButton)
        saveButton.setOnClickListener {
            model.clicked.postValue(true)
            model.saveTask(taskDescription.text.toString())
        }
    }
}