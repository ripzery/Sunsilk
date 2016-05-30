package com.socket9.sunsilk.managers

import android.content.SharedPreferences

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */

object SharePref{

    lateinit var sharePref: SharedPreferences

    val SHARE_PREF_POINT = "point"

    fun savePoint(point: Int){
        sharePref.edit().putInt(SHARE_PREF_POINT, point).apply()
    }

    fun getPoint(): Int{
        return sharePref.getInt(SHARE_PREF_POINT, 0)
    }
}