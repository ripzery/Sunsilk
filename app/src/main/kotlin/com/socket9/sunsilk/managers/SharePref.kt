package com.socket9.sunsilk.managers

import android.content.SharedPreferences
import com.google.gson.Gson
import com.socket9.sunsilk.models.Model
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */

object SharePref: AnkoLogger{

    lateinit var sharePref: SharedPreferences

    val SHARE_PREF_POINT = "point"
    val SHARE_PREF_POINT_CUMULATIVE = "point_cumulative"
    val SHARE_PREF_REDEEM_HISTORY = "redeem_history"
    val SHARE_PREF_FIRST_TIME= "first_time"
    val VIDEOS = listOf(1,2,3,4)
    val SHARE_PREF_IS_UNLOCK_VIDEOS= "unlock_"

    val FIRST_TIME = -1
    val DEFAULT = 0
    val NOT_FIRST_TIME = 1

    fun setFirstTime(isFirst: Int){
        sharePref.edit().putInt(SHARE_PREF_FIRST_TIME, isFirst).apply()
    }

    fun isFirstTime(): Int{
        return sharePref.getInt(SHARE_PREF_FIRST_TIME, DEFAULT)
    }

    fun increasePoint(point: Int){
        sharePref.edit().putInt(SHARE_PREF_POINT_CUMULATIVE, getPointCumulative() + point).apply()
        sharePref.edit().putInt(SHARE_PREF_POINT, point + getPoint()).apply()
    }

    fun decreasePointTo(point: Int){
        sharePref.edit().putInt(SHARE_PREF_POINT, point).apply()
    }

    fun unlockVideo(video: Int){
        sharePref.edit().putBoolean("$SHARE_PREF_IS_UNLOCK_VIDEOS$video", true).apply()
    }

    fun isUnlocked(video: Int): Boolean{
        return sharePref.getBoolean("$SHARE_PREF_IS_UNLOCK_VIDEOS$video", false)
    }

    fun getPoint(): Int{
        return sharePref.getInt(SHARE_PREF_POINT, 0)
    }

    fun getPointCumulative(): Int{
        return sharePref.getInt(SHARE_PREF_POINT_CUMULATIVE, 0)
    }

    fun saveRedeemHistory(model: Model.RedeemPrizeHistory){
        val redeemHistoryList = getRedeemHistory()
        redeemHistoryList.modelList.add(model)
        sharePref.edit().putString(SHARE_PREF_REDEEM_HISTORY, Gson().toJson(redeemHistoryList)).apply()
    }

    fun getRedeemHistory(): Model.RedeemPrizeHistoryList {
        var list: Model.RedeemPrizeHistoryList = Model.RedeemPrizeHistoryList(mutableListOf<Model.RedeemPrizeHistory>())
        try {
            val redeemHistoryString = sharePref.getString(SHARE_PREF_REDEEM_HISTORY, "")
            list = Gson().fromJson(redeemHistoryString, Model.RedeemPrizeHistoryList::class.java)
        }catch(e: Exception){
            e.printStackTrace()
        }finally{
            return list
        }
    }
}