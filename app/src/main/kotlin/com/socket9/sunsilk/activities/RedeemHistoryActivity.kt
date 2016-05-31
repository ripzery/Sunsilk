package com.socket9.sunsilk.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.socket9.sunsilk.R
import com.socket9.sunsilk.fragments.RedeemHistoryFragment
import com.socket9.thetsl.extensions.replaceFragment

class RedeemHistoryActivity : AppCompatActivity() {

    lateinit var redeemHistoryFragment: RedeemHistoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redeem_history)

        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
            return true
        }else{
            return super.onOptionsItemSelected(item)
        }
    }

    private fun initInstance() {

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Redeem history"

        redeemHistoryFragment = RedeemHistoryFragment.newInstance("")
        replaceFragment(fragment = redeemHistoryFragment)
    }
}
