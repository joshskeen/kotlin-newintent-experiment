package com.example.joshskeen.myapplication.kintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


abstract class BaseCompanion(val klass: KClass<out Activity>) {
    fun newIntent(ctx: Context) = Intent(ctx, klass::class.java)
}

abstract class ActivityCompanion<out IntentOptions>(val intentOptions: IntentOptions, klass: KClass<out Activity>) : BaseCompanion(klass) {

    inline fun newIntent(context: Context, configureIntent: IntentOptions.(Intent) -> Unit): Intent =
            Intent(context, javaClass).apply { configureIntent(intentOptions, this) }



}
class IntentExtraString(private val name: String = "") {
    operator fun getValue(intent: Intent, property: KProperty<*>): String? = intent.getStringExtra(name)
    operator fun setValue(intent: Intent, property: KProperty<*>, value: String?) {
        intent.putExtra(name, value)
    }
}