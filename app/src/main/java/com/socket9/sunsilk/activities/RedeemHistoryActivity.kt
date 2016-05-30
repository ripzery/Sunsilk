package com.socket9.sunsilk.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    private fun initInstance() {

        redeemHistoryFragment = RedeemHistoryFragment.newInstance("")
        replaceFragment(fragment = redeemHistoryFragment)
    }
}
