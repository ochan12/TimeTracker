package com.example.timetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.space.SpaceActivity

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