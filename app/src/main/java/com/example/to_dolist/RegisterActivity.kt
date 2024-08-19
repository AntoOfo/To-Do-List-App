package com.example.to_dolist

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {

    // declare firebase instance for authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()   //initialise firebase instance

        val emailInput = findViewById<TextInputEditText>(R.id.emailRegInput)
        val passInput = findViewById<TextInputEditText>(R.id.passregInput)
        val regBtn = findViewById<Button>(R.id.regBtn)

        regBtn.setOnClickListener{
            val email = emailInput.text.toString()
            val password = passInput.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this,"Please enter an email!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                Toast.makeText(this,"Please enter a password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // creates new user w email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(baseContext, "Authentication succesful.", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        if (user != null) {
                            updateUI(user)
                        }
                    } else {
                        // if sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }
        }
        }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in, navigate to the home activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // closes registration activity to prevent users from navigating back to it
        }
    }

    override fun onStart() {
        super.onStart()
        // checks if user is already signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        // Null
    }
}
