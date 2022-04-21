package com.example.timetracker.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracker.di.DaggerApplicationGraph
import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.AuthRepository
import com.example.timetracker.space.SpaceActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class LoginActivity @Inject constructor() : AppCompatActivity(), Taggable {

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var userRepository: AuthRepository

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationGraph.factory().create(applicationContext).inject(this)
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setAlwaysShowSignInMethodScreen(true)
            .setIsSmartLockEnabled(false)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            val provider = response?.providerType
            if (provider.equals(GoogleAuthProvider.PROVIDER_ID) || provider.equals(EmailAuthProvider.PROVIDER_ID)) {
                startActivity(Intent(this, SpaceActivity::class.java))
            } else {
                userRepository.createUser().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe({
                        startActivity(Intent(this, SpaceActivity::class.java))
                    }, {
                        Log.e(TAG, it.localizedMessage)
                    })
            }
            finish()
        } else {
            Log.e(TAG, response?.error.toString())
        }
    }

}