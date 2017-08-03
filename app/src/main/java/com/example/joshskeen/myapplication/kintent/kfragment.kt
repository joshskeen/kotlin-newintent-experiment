package com.example.joshskeen.myapplication.kintent

import android.os.Bundle
import android.support.v4.app.Fragment
import java.io.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class FragmentCompanion<out BundleArguments>(val bundleArguments: BundleArguments,
                                                      klass: KClass<out Fragment>) : BaseFragmentCompanion(klass) {

    inline fun newFragment(configureBundle: BundleArguments.(Bundle) -> Unit): Fragment {
        val newInstance = newFragment()
        val bundle = Bundle()
        bundle.apply {
            configureBundle(bundleArguments, this)
        }
        newInstance.arguments = bundle
        return newInstance
    }

    inline fun <T> Bundle.options(block: BundleArguments.(Bundle) -> T): T = block(bundleArguments, this)
}

class FragmentArgument<T : Serializable>(private val key: String = "", val default: T) {
    operator fun getValue(bundle: Bundle, property: KProperty<*>): T {
        val serializableExtra = bundle.getSerializable(key)
        serializableExtra?.let {
            return it as T
        }
        return default
    }

    operator fun setValue(bundle: Bundle, property: KProperty<*>, value: T) {
        bundle.putSerializable(key, value)
    }


}