package com.socket9.sunsilk.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.socket9.sunsilk.R
import com.socket9.sunsilk.fragments.RedeemDetailFragment
import com.socket9.sunsilk.models.Model
import com.socket9.thetsl.extensions.replaceFragment

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
        if(item?.itemId == android.R.id.home){
            finish()
            return true
        }else{
            return super.onOptionsItemSelected(item)
        }
    }

    /** Method zone **/

    private fun initInstance() {
        val model = intent.getParcelableExtra<Model.RedeemPrize>(INTENT_KEY_PARAM1)
        redeemDetailFragment = RedeemDetailFragment.newInstance(model)
        replaceFragment(fragment = redeemDetailFragment)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "${model.title} Detail"
    }
}
