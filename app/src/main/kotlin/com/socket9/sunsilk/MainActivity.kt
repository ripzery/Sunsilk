package com.socket9.sunsilk

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.socket9.sunsilk.adapter.MainTabAdapter
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.sunsilk.managers.SharePref
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity(), AnkoLogger {
    /** Variable zone **/

    private var mainFragment: MainFragment? = null
    private var mainTabAdapter: MainTabAdapter? = null

    /** Lifecycle method zone **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_clear_point -> {
                SharePref.savePoint(0)
                return true
            }
            R.id.menu_clear_history -> {
                SharePref.sharePref.edit().putString(SharePref.SHARE_PREF_REDEEM_HISTORY, "").apply()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    /** Method zone **/

    private fun initToolbar() {
        supportActionBar?.elevation = 0f
    }

    private fun initInstance() {

        mainTabAdapter = MainTabAdapter(supportFragmentManager, this)

        viewpager.adapter = mainTabAdapter

        tabLayout.setupWithViewPager(viewpager)

        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpager.currentItem = tab!!.position

                if (tab.position == 0) {
                    val mainTab = supportFragmentManager.findFragmentByTag("android:switcher:${R.id.viewpager}:${viewpager.currentItem}") as MainFragment
                    mainTab.updatePoint()
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

//        mainFragment = MainFragment.newInstance("")

//        replaceFragment(fragment = mainFragment!!)
    }
}
