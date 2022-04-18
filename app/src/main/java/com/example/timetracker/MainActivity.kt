package com.example.timetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.auth.LoginActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.mainMenu.MainMenuActivity
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.tutorial.AppTutorial
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), Taggable {


    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        super.onCreate(savedInstanceState)
        var newIntent: Intent
        // setContentView(R.layout.activity_main)
        if (getSharedPreferences(
                getString(R.string.shared_preferences),
                Context.MODE_PRIVATE
            ).getBoolean(
                getString(R.string.shared_preferences_first_time),
                true
            )
        ) {
            newIntent = Intent(this, AppTutorial::class.java)
        } else {
            authRepository.currentUser().subscribe { user ->
                Log.e(TAG, user.toString())
                newIntent = if (user?.equals(null) == false) {
                    Intent(this, MainMenuActivity::class.java)
                } else {
                    Intent(this, LoginActivity::class.java)
                }
                startActivity(newIntent)
            }

        }
    }
}