package com.example.timetracker.createSpace

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.R
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class CreateSpaceActivity @Inject constructor() :
    AppCompatActivity(R.layout.activity_create_space) {

    @Inject
    lateinit var spaceRepository: SpaceRepository

    @Inject
    lateinit var authRepository: AuthRepository

    lateinit var saveButton: Button
    lateinit var spaceName: TextInputEditText

    private val model: CreateSpaceViewModel by viewModels {
        CreateSpaceViewModelFactory(spaceRepository, authRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        initializeElements()
    }

    private fun initializeElements() {
        saveButton = findViewById(R.id.saveSpaceButton)
        spaceName = findViewById(R.id.space_name)
        saveButton.setOnClickListener {
            model.changeName(spaceName.text.toString())
            model.createSpace()
        }
        model.isCreated.observe(this) {
            Log.e("Created space", it.toString())
            if (it == true) {
                finish()
            }
        }

    }
}