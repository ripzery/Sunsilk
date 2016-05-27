package com.socket9.sunsilk

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.sunsilk.adapter.MainTabAdapter
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.thetsl.extensions.replaceFragment
import com.socket9.thetsl.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.support.v4.alert

class MainActivity : AppCompatActivity() {
    private var mainFragment: MainFragment? = null
    private var mainTabAdapter: MainTabAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initInstance()
    }

    private fun initToolbar() {
        supportActionBar?.elevation = 0f
    }

    private fun initInstance() {

        mainTabAdapter = MainTabAdapter(supportFragmentManager, this)

        viewpager.adapter = mainTabAdapter

        tabLayout.setupWithViewPager(viewpager)

//        mainFragment = MainFragment.newInstance("")

//        replaceFragment(fragment = mainFragment!!)
    }
}
