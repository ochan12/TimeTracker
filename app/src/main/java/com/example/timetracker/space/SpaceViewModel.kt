package com.example.timetracker.space

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

class SpaceViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val spaceRepository: SpaceRepository,
) : ViewModel(), Taggable {

    private val savedTaskId: MutableLiveData<String?> = MutableLiveData(null)

    fun getSavedTaskId() = savedTaskId

    private val _spaces: MutableLiveData<List<Space>> by lazy {
        MutableLiveData<List<Space>>().also {
            loadSpaces()
        }
    }

    fun getSpaces() = _spaces

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            it.value = true
        }
    }

    fun isLoading() = _isLoading

    private val _space: MutableLiveData<Space> by lazy {
        MutableLiveData<Space>().also {
            it.value = Space("New Space")
        }
    }

    fun getSpace() = _space

    fun loadSpaces() {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.coroutineContext.also {
            spaceRepository.getSpaces("yo")
        }

    }

    fun saveTask() {
        Log.e(TAG, "savetask")
        _isLoading.postValue(true)

        taskRepository.saveTask(
            _space.value?.getActiveTaskTimer()?.currentTask!!
        ).subscribe({
            Log.e(TAG, "saved task $it")
            _isLoading.postValue(false)
            savedTaskId.postValue(it)
        },
        {
            Log.e(TAG, it.message.toString())
        })

    }
}