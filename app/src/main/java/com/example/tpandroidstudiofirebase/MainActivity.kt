package com.example.tpandroidstudiofirebase

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    fun signOnClick(view: View) {
        val editTextEmail = findViewById<EditText>(R.id.email)
        val editTextPassword = findViewById<EditText>(R.id.password)

        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show()
        } else {
            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}
