package com.example.timetracker.main_menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timetracker.R
import com.example.timetracker.auth.ProfileActivity
import com.example.timetracker.createSpace.CreateSpaceActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.space.Space
import com.example.timetracker.task.ListTasksActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class MainMenuActivity : AppCompatActivity(R.layout.activity_main_menu), Taggable {

    private val SPACES = 0
    private val ACTIVITIES = 1
    private val PROFILE =2

    lateinit var spacesRecyclerView: RecyclerView;
    lateinit var createSpaceButton: FloatingActionButton

    var spacesList: ArrayList<Space> = ArrayList()

    @Inject
    lateinit var spaceRepository: SpaceRepository

    @Inject
    lateinit var auth: AuthRepository

    private val model: MainMenuViewModel by viewModels {
        MainMenuViewModelFactory(spaceRepository, auth)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        initializeElements()
    }

    private fun initializeElements() {
        spacesRecyclerView = findViewById(R.id.rv_spaces)
        createSpaceButton = findViewById(R.id.createSpaceButton)
        spacesRecyclerView.adapter = SpaceAdapter(spacesList)
        spacesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        model.getSpaces().observe(this) {
            spacesList.clear()
            it?.forEach { space -> Log.e("sppace", space.toString()) }
            it?.forEach { space -> spacesList.add(space) }
            spacesRecyclerView.adapter?.notifyDataSetChanged()
        }
        createSpaceButton.setOnClickListener {
            startActivity(Intent(this, CreateSpaceActivity::class.java))
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigation_bottom_bar_root)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var newIntent: Intent
            lateinit var requestCode: Number
            Log.e(TAG, item.itemId.toString())
            Log.e("Spaces", R.id.action_spaces.toString())
            Log.e("tasks", R.id.action_activity.toString())
            Log.e("Profile", R.id.action_profile.toString())
            when (item.itemId) {
                R.id.action_spaces ->{
                    newIntent = Intent(this, MainMenuActivity::class.java)
                    requestCode = SPACES
                }
                R.id.action_activity ->{
                    newIntent = Intent(this, ListTasksActivity::class.java)
                    requestCode = ACTIVITIES
                }
                else -> {
                    newIntent = Intent(this, ProfileActivity::class.java)
                    requestCode = PROFILE
                }
            }
            startActivityIfNeeded(newIntent, requestCode)
            overridePendingTransition(0,0)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        model.loadSpaces()

    }
}