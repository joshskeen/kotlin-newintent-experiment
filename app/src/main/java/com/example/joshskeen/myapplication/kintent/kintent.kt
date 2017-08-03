package com.example.joshskeen.myapplication.kintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import java.io.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


abstract class BaseActivityCompanion(val klass: KClass<out Activity>) {
    fun newIntent(ctx: Context) = Intent(ctx, klass.java)
}

abstract class ActivityCompanion<out IntentOptions>(val intentOptions: IntentOptions, klass: KClass<out Activity>) : BaseActivityCompanion(klass) {

    val myClass = klass::java

    inline fun newIntent(context: Context, configureIntent: IntentOptions.(Intent) -> Unit): Intent =
            newIntent(context).apply { configureIntent(intentOptions, this) }

    inline fun startIntent(context: Context, configure: IntentOptions.(Intent) -> Unit) {
        context.startActivity(newIntent(context, configure))
    }

    inline fun <T> Intent.options(block: IntentOptions.(Intent) -> T): T = block(intentOptions, this)
}


abstract class BaseFragmentCompanion(val fragment: KClass<out Fragment>) {
    fun newFragment() = fragment.java.newInstance()
}

class IntentExtra<T : Serializable>(private val key: String = "", val default: T) {
    operator fun getValue(intent: Intent, property: KProperty<*>): T {
        val serializableExtra = intent.getSerializableExtra(key)
        serializableExtra?.let {
            return it as T
        }
        return default
    }

    operator fun setValue(intent: Intent, property: KProperty<*>, value: T) {
        intent.putExtra(key, value)
    }
}

