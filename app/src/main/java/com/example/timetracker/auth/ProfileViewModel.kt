package com.example.timetracker.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.AuthRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val auth: AuthRepository) : ViewModel() {

    private val _isOnline: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            checkOnlineStatus()
        }
    }

    fun isOnline() = _isOnline

    private fun checkOnlineStatus() {
        auth.isOnline().subscribe { online ->
            _isOnline.postValue(online)
        }
    }

    fun signOut() {
        auth.signOut()
        checkOnlineStatus()
    }
}