package com.example.joshskeen.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.example.joshskeen.myapplication.kintent.ActivityCompanion
import com.example.joshskeen.myapplication.kintent.IntentExtra

private const val TAG = "ASecondActivity"

class ASecondActivity : AppCompatActivity() {

    companion object : ActivityCompanion<IntentOptions>(IntentOptions, ASecondActivity::class)

    object IntentOptions {
        var Intent.secondActivityMessage by IntentExtra("secondActivityMessage", "_default_")
        var Intent.secondActivityRadVal by IntentExtra("secondActivityRadVal", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = TAG
        setContentView(R.layout.activity_main)

        val findViewById = findViewById<Button>(R.id.button)
        findViewById.setOnClickListener {
            YetAnotherActivity.startIntent(this, {
                it.message = "hello from a second activity!"
                it.rad_value = 42
            })
        }
        val newFragment = MyFragment.newFragment {
            it.arg1 = "SNAP!"
        }

        savedInstanceState ?: supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, newFragment)
                .commit()

        intent.options {
            Log.d(TAG, "for message property got: " + it.secondActivityMessage)
            Log.d(TAG, "for rad value got: " + it.secondActivityRadVal)
        }
    }

}