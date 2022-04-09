package com.example.timetracker.task

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timetracker.R
import com.example.timetracker.auth.ProfileActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.main_menu.MainMenuActivity
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import com.example.timetracker.space.Space
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class ListTasksActivity : AppCompatActivity() {

    private val SPACES = 0
    private val ACTIVITIES = 1
    private val PROFILE = 2

    @Inject
    lateinit var taskRepository: TaskRepository

    @Inject
    lateinit var spaceRepository: SpaceRepository

    private lateinit var tasksRecyclerView: RecyclerView

    private var taskList: ArrayList<Task> = ArrayList()
    private var spaceList: ArrayList<Space> = ArrayList()

    private val model: ListTasksViewModel by viewModels {
        ListTasksViewModelFactory(taskRepository, spaceRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        setContentView(R.layout.activity_tasks)
        initElements()
    }

    fun initElements() {
        tasksRecyclerView = findViewById(R.id.rv_task_list)

        tasksRecyclerView.adapter = TaskAdapter(dataSet = taskList, spaces = spaceList)
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        model.getTasks().observe(this) {
            taskList.clear()
            it?.forEach { task -> taskList.add(task) }
            tasksRecyclerView.adapter?.notifyDataSetChanged()
        }
        model.getUserSpaces().observe(this) {
            spaceList.clear()
            it?.forEach { space -> spaceList.add((space)) }
            tasksRecyclerView.adapter?.notifyDataSetChanged()
        }


        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.navigation_bottom_bar_root)
        bottomNavigationView.selectedItemId = R.id.action_activity
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var newIntent: Intent
            lateinit var requestCode: Number

            when (item.itemId) {
                R.id.action_spaces -> {
                    newIntent = Intent(this, MainMenuActivity::class.java)
                    requestCode = SPACES
                }
                R.id.action_activity -> {
                    newIntent = Intent(this, ListTasksActivity::class.java)
                    requestCode = ACTIVITIES
                }
                else -> {
                    newIntent = Intent(this, ProfileActivity::class.java)
                    requestCode = PROFILE
                }
            }
            startActivityIfNeeded(newIntent, requestCode)
            overridePendingTransition(0, 0)
            true
        }
    }


}