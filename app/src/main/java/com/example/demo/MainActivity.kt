package com.example.demo

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val data = mutableListOf(R.drawable.img_a, R.drawable.img_l, R.drawable.img_k, R.drawable.img_i)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("STATUS", "OnCreate")

        btnB.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fullFrameLayout, FirstFragment())
                .addToBackStack(null)
                .commit()
        }

        btnFrag2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.secondFragment, SecondFragment())
                .addToBackStack(null)
                .commit()
        }
        progressBar.max = 100
        btnStart.setOnClickListener {
            val mainHandler = Handler(mainLooper)
            thread {
                for (i in 1..100) {
                    println("I:$i")
                    progressBar.progress += 1
                    try {
                        Thread.sleep(100)
                    }
                    catch (e: Exception){
                        e.printStackTrace()
                    }
                    mainHandler.post { textStart.text = "$i%" }
                }


            }
        }

        val adapter = ViewAdapter(data)
        viewPager.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        Log.d("STT", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("STT", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("STT", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("STT", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("STT", "Destroy")
    }
}

