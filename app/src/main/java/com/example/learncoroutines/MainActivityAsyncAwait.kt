package com.example.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityAsyncAwait : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_async_await)

        GlobalScope.launch {

            val dataUser = async { getUserFromNetwork() }
            val localUser = async { getUserFromDB() }

            if (dataUser.await() == localUser.await()) {
                Log.d("DataStatus","Equal")
            }else{
                Log.d("DataStatus","Not Equal")
            }
        }


    }

    private suspend fun getUserFromNetwork(): String {
        delay(2000)
        return "myData"
    }

    private suspend fun getUserFromDB(): String {
        delay(2000)
        return "myData"
    }
}