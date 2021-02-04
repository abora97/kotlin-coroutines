package com.example.learncoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this,MainActivityAsyncAwait::class.java))

        Log.d("mainActivity", "main thread 1")

        // this make your code counties and when finish print text
        GlobalScope.launch {
            // cant make changes in UI thread
            printMyTextAfterDelay("GlobalScope")

            launch {
                printTextInBraller("GlobalScope barraler 1")
            }

            launch {
                printTextInBraller("GlobalScope barraler 2")
            }

        }

        Log.d("mainActivity", "main thread 2")

        // this stop app to print text and make app counties
        runBlocking {
            // can make changes in UI thread
            printMyTextAfterDelay("runBlocking")
        }

        Log.d("mainActivity", "main thread 3")
    }

    suspend fun printMyTextAfterDelay(myText: String) {
        delay(2000)
        Log.d("mainActivity", myText)
    }

    suspend fun printTextInBraller(myText: String) {
//        GlobalScope.launch {
            delay(2000)
            Log.d("mainActivity", myText)
//        }
    }
}