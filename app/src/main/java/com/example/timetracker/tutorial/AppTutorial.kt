package com.example.timetracker.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.timetracker.MainActivity
import com.example.timetracker.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class AppTutorial : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!
        Log.e("Tutorial", "e")
        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.createInstance(
                title = getString(R.string.slide_welcome_title),
                description = getString(R.string.slide_welcome_description),
                titleColorRes = R.color.tutorial_title,
                descriptionColorRes = R.color.tutorial_description,
                imageDrawable = R.drawable.tutorial_timelapse,
                backgroundColorRes = R.color.tutorial_background
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = getString(R.string.slide_spaces_title),
                description = getString(R.string.slide_spaces_description),
                titleColorRes = R.color.tutorial_title,
                descriptionColorRes = R.color.tutorial_description,
                imageDrawable = R.drawable.tutorial_space,
                backgroundColorRes = R.color.tutorial_background
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = getString(R.string.slide_tasks_title),
                description = getString(R.string.slide_tasks_description),
                titleColorRes = R.color.tutorial_title,
                descriptionColorRes = R.color.tutorial_description,
                imageDrawable = R.drawable.tutorial_timer,
                backgroundColorRes = R.color.tutorial_background

            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = getString(R.string.slide_account_title),
                description = getString(R.string.slide_account_description),
                titleColorRes = R.color.tutorial_title,
                descriptionColorRes = R.color.tutorial_description,
                imageDrawable = R.drawable.tutorial_person,
                backgroundColorRes = R.color.tutorial_background
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = getString(R.string.slide_final_title),
                description = getString(R.string.slide_final_description),
                titleColorRes = R.color.tutorial_title,
                descriptionColorRes = R.color.tutorial_description,
                imageDrawable = R.drawable.tutorial_play,
                backgroundColorRes = R.color.tutorial_background
            )
        )

        isWizardMode = true
        setImmersiveMode()
        isColorTransitionsEnabled = true


    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        disableFirstTime()
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        disableFirstTime()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun disableFirstTime() {
        val sharedPref =
            getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
                ?: return
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.shared_preferences_first_time), false)
            apply()
        }
    }
}