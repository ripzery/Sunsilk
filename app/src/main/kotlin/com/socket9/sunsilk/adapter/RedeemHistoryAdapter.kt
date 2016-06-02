package com.socket9.sunsilk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.models.Model
import com.socket9.sunsilk.viewgroups.RedeemHistoryViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */

class RedeemHistoryAdapter : RecyclerView.Adapter<RedeemHistoryAdapter.RedeemHistoryViewHolder>, AnkoLogger {

    lateinit var redeemList: MutableList<Model.RedeemPrizeHistory>
    private var listener: RedeemHistoryAdapter.RedeemHistoryClickInterface? = null

    companion object {
        val defaultImgUrl = "https://source.unsplash.com/category/food/400x225"

        fun newInstance(redeemList: MutableList<Model.RedeemPrizeHistory>, listener: RedeemHistoryClickInterface): RedeemHistoryAdapter {
            return RedeemHistoryAdapter(redeemList, listener)
        }
    }

    override fun onBindViewHolder(holder: RedeemHistoryViewHolder, position: Int) {
        holder.setModel(redeemList[position])
    }

    override fun getItemCount(): Int {
        return redeemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RedeemHistoryViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_redeem_history, parent, false)
        return RedeemHistoryViewHolder(view)
    }


    constructor(redeemList: MutableList<Model.RedeemPrizeHistory>, listener: RedeemHistoryClickInterface) {
        this.redeemList = redeemList
        this.listener = listener
    }

    inner class RedeemHistoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var redeemViewGroup: RedeemHistoryViewGroup;

        init {
            // find itemView here
            redeemViewGroup = itemView!!.find<RedeemHistoryViewGroup>(R.id.redeemHistoryViewGroup)

            redeemViewGroup.getClickObservable().subscribe {
                listener?.onClick(adapterPosition, redeemList[adapterPosition])
            }
        }

        fun setModel(model: Model.RedeemPrizeHistory) {
            redeemViewGroup.setModel(model)
        }

    }

    interface RedeemHistoryClickInterface {
        fun onClick(position: Int, model: Model.RedeemPrizeHistory)
    }
}