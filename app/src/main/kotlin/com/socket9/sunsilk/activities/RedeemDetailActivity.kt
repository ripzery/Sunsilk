package com.socket9.sunsilk.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.socket9.sunsilk.R
import com.socket9.sunsilk.extensions.replaceFragment
import com.socket9.sunsilk.fragments.RedeemDetailFragment
import com.socket9.sunsilk.models.Model
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class RedeemDetailActivity : AppCompatActivity() {

    /** Variable zone **/
    lateinit var redeemDetailFragment: RedeemDetailFragment

    companion object {
        val INTENT_KEY_PARAM1 = "redeemPrize"
    }

    /** Lifecycle method zone **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redeem_detail)

        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    /** Method zone **/

    private fun initInstance() {
        val model = intent.getParcelableExtra<Model.RedeemPrize>(INTENT_KEY_PARAM1)
        val isFromHistory = intent.getBooleanExtra("isHistory", false)

        redeemDetailFragment = RedeemDetailFragment.newInstance(model, isFromHistory)
        replaceFragment(fragment = redeemDetailFragment)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "${model.title} Detail"
    }
}
