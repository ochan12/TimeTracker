package com.example.timetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.persistance.room.AppDatabase
import com.example.timetracker.task.Task_Factory.create

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val newIntent = Intent(this, SpaceActivity::class.java)
        startActivity(newIntent)
    }
}