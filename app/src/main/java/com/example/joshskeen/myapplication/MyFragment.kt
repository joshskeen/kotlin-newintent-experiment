package com.example.joshskeen.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.example.joshskeen.myapplication.kintent.FragmentArgument
import com.example.joshskeen.myapplication.kintent.FragmentCompanion

private const val TAG = "MyFragment"

class MyFragment : Fragment() {

    companion object : FragmentCompanion<FragmentArguments>(FragmentArguments, MyFragment::class)

    object FragmentArguments {
        var Bundle.arg1 by FragmentArgument("fragArg", "_default_")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newFragment = MyFragment.newFragment {
            arguments.options {
                val arg1 = it.arg1
                println(arg1)
            }
        }
    }
}

