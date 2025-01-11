package com.example.kotlinbase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class FileDataActivity : AppCompatActivity() {

    private lateinit var editText: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_data)
        editText = findViewById(R.id.edittext)
        Log.i("hejie","come in onCreate")
        val inputText = load()
        if(inputText.isNotEmpty()){
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this,"Restroring successed",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("hejie","come in onDestroy")
    }

    override fun onStop() {
        super.onStop()
        val inputText = editText.text.toString()
        save(inputText)
        Log.i("hejie","come in onStop")
    }
    private fun save(inputText: String){
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val write = BufferedWriter(OutputStreamWriter(output))
            write.use {
                it.write(inputText)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun load(): String{
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val read = BufferedReader(InputStreamReader(input))
            read.use {
                read.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return content.toString()
    }

}