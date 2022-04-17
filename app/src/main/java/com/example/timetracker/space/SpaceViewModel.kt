package com.example.timetracker.space

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.persistance.SpaceRepository
import com.example.timetracker.persistance.TaskRepository
import javax.inject.Inject

class SpaceViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val spaceRepository: SpaceRepository,
    private val authRepository: AuthRepository,
    private val spaceId: String
) : ViewModel(), Taggable {

    private val savedTaskId: MutableLiveData<String?> = MutableLiveData(null)


    fun getSavedTaskId() = savedTaskId


    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            it.value = true
        }
    }

    fun isLoading() = _isLoading

    private val _space: MutableLiveData<Space> by lazy {
        MutableLiveData<Space>().also {
            loadSpace(spaceId)
        }
    }

    fun loadSpace(spaceId: String) {
        spaceRepository.getSpace(spaceId).subscribe({
            _space.postValue(it)
        }, {
            Log.e(TAG, it.message.toString())
        })
    }

    fun getSpace() = _space


    fun saveTask() {
        _isLoading.postValue(true)
        val task = _space.value?.getActiveTaskTimer()?.currentTask!!
        task.setSpace(spaceId)
        authRepository.getUserId().subscribe { userId ->
            task.setUserId(userId)
            taskRepository.saveTask(
                task
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
}