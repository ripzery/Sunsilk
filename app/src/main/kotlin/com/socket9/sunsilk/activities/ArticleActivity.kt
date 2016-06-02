package com.socket9.sunsilk.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.socket9.sunsilk.R
import com.socket9.sunsilk.extensions.replaceFragment
import com.socket9.sunsilk.fragments.ArticleFragment
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun initInstance() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentArticle: ArticleFragment = ArticleFragment.newInstance("")
        replaceFragment(fragment = fragmentArticle)
    }
}
