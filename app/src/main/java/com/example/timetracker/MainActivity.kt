package com.example.timetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.auth.LoginActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.main_menu.MainMenuActivity
import com.example.timetracker.persistance.AuthRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.NumberFormatException
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity() {


    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val newIntent: Intent = if (authRepository.currentUser() !== null) {
            Intent(this, MainMenuActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(newIntent)
    }
}