package com.socket9.sunsilk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.sunsilk.adapter.MainTabAdapter
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.thetsl.extensions.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.appcompat.v7.toolbar

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
//        setSupportActionBar(myToolbar)
        supportActionBar?.elevation = 0f
//        supportActionBar?.title = "Sunsilk"
    }

    private fun initInstance() {

        mainTabAdapter = MainTabAdapter(supportFragmentManager, this)

        viewpager.adapter = mainTabAdapter

        tabLayout.setupWithViewPager(viewpager)

//        mainFragment = MainFragment.newInstance("")

//        replaceFragment(fragment = mainFragment!!)
    }
}
