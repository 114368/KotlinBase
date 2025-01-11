package com.example.kotlinbase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SharePreferencesActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var restoreButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preferences)
        button = findViewById(R.id.saveButton)
        restoreButton = findViewById(R.id.restoreButton)
        button.setOnClickListener {
            val editor = getPreferences(Context.MODE_PRIVATE).edit()
            editor.putString("name","tom")
            editor.putInt("age",28)
            editor.putBoolean("married",false)
            editor.apply()
        }
        restoreButton.setOnClickListener {
            val prefs = getPreferences(Context.MODE_PRIVATE)
            val name = prefs.getString("name","")
            val age = prefs.getInt("age",0)
            val married = prefs.getBoolean("married",false)
            Log.d("hejie","name is $name")
            Log.d("hejie","age is $age")
            Log.d("hejie","married is $married")
        }
    }
}