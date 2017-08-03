package com.example.joshskeen.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.example.joshskeen.myapplication.kintent.ActivityCompanion
import com.example.joshskeen.myapplication.kintent.IntentExtra

private const val TAG = "YetAnotherActivity"

class YetAnotherActivity : AppCompatActivity() {

    companion object : ActivityCompanion<IntentOptions>(IntentOptions, YetAnotherActivity::class)

    object IntentOptions {
        var Intent.message by IntentExtra("message", "")
        var Intent.rad_value by IntentExtra("rad_val", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = TAG
        setContentView(R.layout.activity_main)

        val findViewById = findViewById<Button>(R.id.button)

        findViewById.setOnClickListener {
            ASecondActivity.startIntent(this, {
                it.secondActivityMessage = "hello from yet another activity"
                it.secondActivityRadVal = 100
            })
        }

        intent.options {
            Log.d(TAG, "for message property got: " + it.message)
            Log.d(TAG, "for rad value got: " + it.rad_value)
        }


    }

}