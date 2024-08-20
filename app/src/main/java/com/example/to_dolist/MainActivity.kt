package com.example.to_dolist

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val title = findViewById<TextInputEditText>(R.id.titleInput)
        val desc = findViewById<TextInputEditText>(R.id.descInput)
        val date = findViewById<TextInputEditText>(R.id.dateInput)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val viewBtn = findViewById<Button>(R.id.viewBtn)

        addBtn.setOnClickListener{

        }

        }
    }
