package com.socket9.sunsilk.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.socket9.sunsilk.R
import com.socket9.sunsilk.fragments.ArticleFragment
import com.socket9.sunsilk.fragments.GalleryFragment
import com.socket9.thetsl.extensions.replaceFragment

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
            return true
        }else{
            return super.onOptionsItemSelected(item)
        }
    }

    private fun initInstance() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val fragmentGallery: GalleryFragment = GalleryFragment.newInstance("")
        replaceFragment(fragment = fragmentGallery)
    }
}
