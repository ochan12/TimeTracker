package com.example.timetracker.space

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.SaveTaskActivity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SpaceViewModel: ViewModel() {
    private val spaces: MutableLiveData<List<Space>> by lazy {
        MutableLiveData<List<Space>>().also {
            loadSpaces()
        }
    }

    private fun loadSpaces() {
//        val scope = CoroutineScope(Job() + Dispatchers.Main)
//        val job = async {
//            taskRepository.saveTask(space.getActiveTaskTimer()?.currentTask!!)
//        }
//        coroutineScope {
//
//        }
//        Flow()
//        Single.just(job).subscribe { it ->
//            Log.e("Taski", it.toString())
//
//            val newIntent =
//                Intent(applicationContext, SaveTaskActivity::class.java)
//
//            // newIntent.putExtra("taskId", it)
//            startActivity(newIntent)
//        }
    }
}