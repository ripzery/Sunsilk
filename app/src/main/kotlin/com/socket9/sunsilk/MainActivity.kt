package com.socket9.sunsilk

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.socket9.sunsilk.activities.VideoActivity
import com.socket9.sunsilk.adapter.MainTabAdapter
import com.socket9.sunsilk.fragments.GalleryFragment
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.sunsilk.managers.SharePref
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog_unlock_video.*
import org.jetbrains.anko.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity(), AnkoLogger {
    /** Variable zone **/

    private var mainFragment: MainFragment? = null
    private var mainTabAdapter: MainTabAdapter? = null
    private val spChanged = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        if (key == SharePref.SHARE_PREF_POINT_CUMULATIVE) {
            /* check video reach */
            val pointCumulative = SharePref.getPointCumulative()
            if(pointCumulative >= GalleryFragment.galleryList[0].point && !SharePref.isUnlocked(1)){
                showUnlockVideoDialog(1)

            }else if(pointCumulative >= GalleryFragment.galleryList[1].point && !SharePref.isUnlocked(2)){
                showUnlockVideoDialog(2)

            }else if(pointCumulative >= GalleryFragment.galleryList[2].point && !SharePref.isUnlocked(3)){
                showUnlockVideoDialog(3)

            }else if(pointCumulative >= GalleryFragment.galleryList[3].point && !SharePref.isUnlocked(4)){
                showUnlockVideoDialog(4)

            }
        }
    }


    /** Lifecycle method zone **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initInstance()
    }

    override fun onResume() {
        super.onResume()
        SharePref.sharePref.registerOnSharedPreferenceChangeListener(spChanged)
    }

    override fun onPause() {
        super.onPause()
        SharePref.sharePref.unregisterOnSharedPreferenceChangeListener(spChanged)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_clear_point -> {
                SharePref.sharePref.edit().putInt(SharePref.SHARE_PREF_POINT_CUMULATIVE, 0).apply()
                SharePref.sharePref.edit().putInt(SharePref.SHARE_PREF_POINT, 0).apply()
                startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
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


        /* Congratulation 10 Point */

        if (SharePref.isFirstTime() == SharePref.FIRST_TIME) {

            SharePref.setFirstTime(SharePref.NOT_FIRST_TIME)

            SharePref.increasePoint(10)

            showFirstTimeDialog()
        }

    }

    private fun showFirstTimeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.layout_dialog_first_time, rootContainer, false)
        val dialog = alert {
            customView(dialogView)
        }

        dialog.show()

        val btnOk = dialogView.find<Button>(R.id.btnOk)

        btnOk.onClick {
            dialog.dismiss()
            val mainTab = supportFragmentManager.findFragmentByTag("android:switcher:${R.id.viewpager}:${viewpager.currentItem}") as MainFragment
            mainTab.updatePoint()
        }
    }

    private fun showUnlockVideoDialog(video: Int) {
        SharePref.unlockVideo(video)

        val images = listOf(R.drawable.gallery_video_1, R.drawable.gallery_video_2, R.drawable.gallery_video_3, R.drawable.gallery_video_4)
        val index = video - 1

        val dialogView = layoutInflater.inflate(R.layout.layout_dialog_unlock_video, rootContainer, false)
        val dialog = alert {
            customView(dialogView)
        }

        val btnOpen = dialogView.find<Button>(R.id.btnOpen)
        val ivVideo = dialogView.find<ImageView>(R.id.ivVideo)

        Glide.with(this).load(images[index]).into(ivVideo)

        dialog.show()

        btnOpen.onClick {
            dialog.dismiss()
            startActivity<VideoActivity>("url" to GalleryFragment.galleryList[index].url)
        }
    }



}
