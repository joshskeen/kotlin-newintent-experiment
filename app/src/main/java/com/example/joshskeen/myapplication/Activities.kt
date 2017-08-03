package com.example.joshskeen.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.joshskeen.myapplication.kintent.ActivityCompanion
import com.example.joshskeen.myapplication.kintent.IntentExtraString


open class BaseActivity : AppCompatActivity() {
    object IntentOptions
}


class YetAnotherActivity : BaseActivity() {
    object IntentOptions {
        val Intent.message by IntentExtraString()
    }
    companion object : ActivityCompanion<IntentOptions>(IntentOptions, YetAnotherActivity::class)

}
