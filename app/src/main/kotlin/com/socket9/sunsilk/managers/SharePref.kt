package com.socket9.sunsilk.managers

import android.content.SharedPreferences
import com.google.gson.Gson
import com.socket9.sunsilk.models.Model

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */

object SharePref{

    lateinit var sharePref: SharedPreferences

    val SHARE_PREF_POINT = "point"
    val SHARE_PREF_REDEEM_HISTORY = "redeem_history"

    fun savePoint(point: Int){
        sharePref.edit().putInt(SHARE_PREF_POINT, point).apply()
    }

    fun getPoint(): Int{
        return sharePref.getInt(SHARE_PREF_POINT, 0)
    }

    fun saveRedeemHistory(model: Model.RedeemPrize){
        val redeemHistoryList = getRedeemHistory()
        redeemHistoryList.add(model)
        sharePref.edit().putString(SHARE_PREF_REDEEM_HISTORY, Gson().toJson(redeemHistoryList)).apply()
    }

    fun getRedeemHistory(): MutableList<Model.RedeemPrize> {
        val redeemHistoryString = sharePref.getString(SHARE_PREF_REDEEM_HISTORY, "")
        var list: Model.RedeemPrizeList = Model.RedeemPrizeList(mutableListOf<Model.RedeemPrize>())
        try {
            list = Gson().fromJson(redeemHistoryString, Model.RedeemPrizeList::class.java)
        }catch(e: Exception){
            e.printStackTrace()
        }finally{
            return list.modelList
        }
    }
}