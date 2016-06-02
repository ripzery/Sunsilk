package com.socket9.sunsilk

import android.app.Application
import android.content.Context
import com.socket9.sunsilk.managers.Contextor
import com.socket9.sunsilk.managers.SharePref
import net.danlew.android.joda.JodaTimeAndroid
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        SharePref.sharePref = getSharedPreferences("Sunsilk", Context.MODE_PRIVATE);
        Contextor.context = this
        JodaTimeAndroid.init(this);
//        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/adman/db_adman_x.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build())
    }
}