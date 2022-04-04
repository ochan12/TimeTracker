package com.example.timetracker.main_menu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timetracker.R
import com.example.timetracker.createSpace.CreateSpaceActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.space.Space
import javax.inject.Inject

class MainMenuActivity : AppCompatActivity(R.layout.activity_main_menu) {

    lateinit var spacesRecyclerView: RecyclerView;
    lateinit var createSpaceButton: Button

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
            it?.forEach { space -> spacesList.add(space) }
            spacesRecyclerView.adapter?.notifyDataSetChanged()
        }
        createSpaceButton.setOnClickListener {
            startActivity(Intent(this, CreateSpaceActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        model.loadSpaces()

    }
}