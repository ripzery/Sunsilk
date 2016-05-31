package com.socket9.sunsilk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.managers.Contextor
import com.socket9.sunsilk.models.Model
import com.socket9.sunsilk.viewgroups.RedeemHistoryViewGroup
import com.socket9.sunsilk.viewgroups.RedeemPrizeViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */

class RedeemHistoryAdapter : RecyclerView.Adapter<RedeemHistoryAdapter.RedeemHistoryViewHolder>, AnkoLogger {

    lateinit var redeemList: MutableList<Model.RedeemPrizeHistory>
    private var listener: RedeemAdapter.RedeemClickInterface? = null

    companion object {
        val defaultImgUrl = "https://source.unsplash.com/category/food/400x225"

        fun newInstance(redeemList: MutableList<Model.RedeemPrizeHistory>): RedeemHistoryAdapter {
            return RedeemHistoryAdapter(redeemList)
        }
    }

    override fun onBindViewHolder(holder: RedeemHistoryViewHolder, position: Int) {
        holder.setModel(redeemList[position])
    }

    override fun getItemCount(): Int {
        return redeemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RedeemHistoryViewHolder {
        val view:View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_redeem_history, parent, false)
        return RedeemHistoryViewHolder(view)
    }


    constructor(redeemList: MutableList<Model.RedeemPrizeHistory>) {
        this.redeemList = redeemList
    }

    inner class RedeemHistoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


        // TODO : Find redeem view group
        lateinit var redeemViewGroup: RedeemHistoryViewGroup;

        init {
            // find itemView here
            redeemViewGroup = itemView!!.find<RedeemHistoryViewGroup>(R.id.redeemHistoryViewGroup)
        }

        fun setModel(model : Model.RedeemPrizeHistory){
            redeemViewGroup.setModel(model)
        }

    }
}