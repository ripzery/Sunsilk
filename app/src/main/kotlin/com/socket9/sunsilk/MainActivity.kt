package com.socket9.sunsilk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.thetsl.extensions.replaceFragment

class MainActivity : AppCompatActivity() {
    private var mainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstance()
    }


    private fun initInstance() {
        mainFragment = MainFragment.newInstance("")

        replaceFragment(fragment = mainFragment!!)
    }
}
