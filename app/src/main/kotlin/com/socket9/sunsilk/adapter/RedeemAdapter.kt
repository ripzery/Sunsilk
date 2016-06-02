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
                Model.RedeemPrize("ปลาทู Gold", 10, "A brand new ปลาทู gold edition will give you an amazingly meal. Experienced the most valuable ปลาทู. Redeem now and go to cook rice!", "http://f.ptcdn.info/807/037/000/nyhn6610hgoX464S4FH-o.jpg"),
                Model.RedeemPrize("ยาหม่อง",
                        30,
                        "Experienced the best ยาหม่อง in our galaxy.",
                        "http://q.lnwfile.com/_/q/_raw/0y/ye/rm.png"),
                Model.RedeemPrize("Chromecast",
                        40,
                        "Cast your favorite entertainment from your phone straight to your TV.",
                        "https://www.google.com/chromecast/static/images/tv/chromecast.jpg"),
                Model.RedeemPrize("Macbook 2015",
                        50,
                        "It’s the future of the notebook. And now, with sixth‑generation Intel processors, improved graphics performance, faster flash storage, and up to 10 hours of battery life,* MacBook is even more capable.",
                        "http://store.storeimages.cdn-apple.com/4973/as-images.apple.com/is/image/AppleInc/aos/published/images/m/ac/macbook/select/macbook-select-gold-201604?wid=1200&hei=630&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=VmqkF1")
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