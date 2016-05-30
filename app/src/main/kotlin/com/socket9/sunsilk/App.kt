package com.socket9.sunsilk

import android.app.Application
import android.content.Context
import com.socket9.sunsilk.managers.Contextor
import com.socket9.sunsilk.managers.SharePref

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        SharePref.sharePref = getSharedPreferences("Sunsilk", Context.MODE_PRIVATE);
        Contextor.context = this
    }
}