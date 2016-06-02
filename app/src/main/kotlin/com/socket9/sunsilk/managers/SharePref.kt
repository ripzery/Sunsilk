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

    fun savePoint(point: Int){
        sharePref.edit().putInt(SHARE_PREF_POINT, point).apply()
        sharePref.edit().putInt(SHARE_PREF_POINT_CUMULATIVE, getPointCumulative() + point)
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