package com.example.timetracker.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.R
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.main_menu.MainMenuActivity
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.task.ListTasksActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    private val SPACES = 0
    private val ACTIVITIES = 1
    private val PROFILE = 2

    @Inject
    lateinit var authRepository: AuthRepository

    lateinit var signOutButton: Button
    lateinit var isOnlineText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        setContentView(R.layout.activity_profile)
        initElements()
    }

    private val model: ProfileViewModel by viewModels {
        ProfileViewModelFactory(authRepository)
    }

    private fun initElements() {
        signOutButton = findViewById(R.id.signOutButton)
        signOutButton.setOnClickListener {
            model.signOut()
            val newIntent = Intent(this, LoginActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(newIntent)
            finish()
        }
        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.navigation_bottom_bar_root)
        bottomNavigationView.selectedItemId = R.id.action_profile
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


        isOnlineText = findViewById(R.id.isOnlineText)
        model.isOnline().observe(this) {
            isOnlineText.text = if (it == true) "isOnline" else "isOffline"
        }

    }
}