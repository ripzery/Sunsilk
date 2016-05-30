package com.socket9.sunsilk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.managers.Contextor
import com.socket9.sunsilk.models.Model
import com.socket9.sunsilk.viewgroups.RedeemPrizeViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */

class RedeemAdapter : RecyclerView.Adapter<RedeemAdapter.RedeemViewHolder>, AnkoLogger {

    lateinit var redeemList: MutableList<Model.RedeemPrize>
    private var listener: RedeemAdapter.RedeemClickInterface? = null

    companion object {
        val defaultImgUrl = "https://source.unsplash.com/category/food/400x225"
        val defaultList: MutableList<Model.RedeemPrize> = mutableListOf(
                Model.RedeemPrize("Prize A", 10, Contextor.context.getString(R.string.lorem), defaultImgUrl),
                Model.RedeemPrize("Prize B", 20, "Description B", defaultImgUrl),
                Model.RedeemPrize("Prize C", 30, "Description C", defaultImgUrl)
        )

        fun newInstance(redeemList: MutableList<Model.RedeemPrize> = defaultList, listener: RedeemClickInterface): RedeemAdapter {
            return RedeemAdapter(redeemList, listener)
        }
    }

    override fun onBindViewHolder(holder: RedeemViewHolder, position: Int) {
        holder.setModel(redeemList[position])
    }

    override fun getItemCount(): Int {
        return redeemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RedeemViewHolder {
        val view:View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_redeem, parent, false)
        return RedeemViewHolder(view)
    }


    constructor(redeemList: MutableList<Model.RedeemPrize>, listener: RedeemClickInterface) {
        this.redeemList = redeemList
        this.listener = listener
    }

    inner class RedeemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


        // TODO : Find redeem view group
        lateinit var redeemViewGroup: RedeemPrizeViewGroup;

        init {
            // find itemView here
            redeemViewGroup = itemView!!.find<RedeemPrizeViewGroup>(R.id.redeemPrizeViewGroup)

            redeemViewGroup.getClickObservable().subscribe {
                listener?.onClick(adapterPosition, redeemList[adapterPosition])
                info { redeemList[adapterPosition].toString() }
            }
        }

        fun setModel(model : Model.RedeemPrize){
            redeemViewGroup.setModel(model)
        }

    }

    interface RedeemClickInterface{
        fun onClick(position:Int, model: Model.RedeemPrize)
    }
}