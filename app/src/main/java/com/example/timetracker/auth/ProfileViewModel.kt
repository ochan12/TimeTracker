package com.example.timetracker.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracker.persistance.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val auth: AuthRepository) : ViewModel() {

    private val _isOnline: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            it.value = checkOnlineStatus()
        }
    }

    fun isOnline() = _isOnline

    private fun checkOnlineStatus(): Boolean {
        return auth.isOnline()
    }

    fun signOut() {
        auth.signOut()
        _isOnline.postValue(checkOnlineStatus())
    }
}