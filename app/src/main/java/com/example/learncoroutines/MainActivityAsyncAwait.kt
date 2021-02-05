package com.example.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivityAsyncAwait : AppCompatActivity() {

    val parentJob: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_async_await)

        GlobalScope.launch {

            val dataUser = async { getUserFromNetwork() }
            val localUser = async { getUserFromDB() }

            if (dataUser.await() == localUser.await()) {
                Log.d("DataStatus", "Equal")
            } else {
                Log.d("DataStatus", "Not Equal")
            }
        }

        //  Jobs & structured concurrency

        val mCoroutines: CoroutineScope = CoroutineScope(Dispatchers.IO + parentJob)

        mCoroutines.launch {
            getUserFromNetwork()
            getUserFromDB()
        }


        val job: Job = GlobalScope.launch {

            val child1 = launch { getUserFromNetwork() }
            val child2 = launch { getUserFromDB() }

            // make suspend wait for finish
//            child1.join()
//            child2.join()

//            joinAll(child1,child2)

//            child1.cancelAndJoin()

        }


        // to cancel all child
        // job.cancel()
//         parentJob.cancel()

    }

    private suspend fun getUserFromNetwork(): String {
        delay(2000)
        return "myData"
    }

    private suspend fun getUserFromDB(): String {
        delay(2000)
        return "myData"
    }

    override fun onStop() {
        super.onStop()
        parentJob.cancel()
    }


}