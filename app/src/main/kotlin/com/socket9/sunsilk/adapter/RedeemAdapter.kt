package com.socket9.sunsilk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
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
        val defaultList: MutableList<Model.RedeemPrize> = mutableListOf(
                Model.RedeemPrize("แลกรับครีมนวดผมฟรี 10 คะแนน", 10, "แลกรับครีมนวดผมฟรี 30 คะแนน เมื่อซื้อแชมพูขนาด 70 ml. ขึ้นไปในราคาปกติสูตรใดก็ได้ แลกรับฟรี ครีมนวดผมขนาด 60 ml. 1 ขวดสูตรเดียวกัน", R.drawable.redeem_sunsilk),
                Model.RedeemPrize("แลกคะแนนกับ Line Pay 20 คะแนน",
                        20,
                        "แลกคะแนนกับ Line Pay 20 คะแนนแลกรับกันไปเลยทันทีกับเงินใน Line Pay มูลค่า 10 บาท",
                        R.drawable.redeem_line_pay_2),
                Model.RedeemPrize("แลกคะแนนกับ Ais 30 คะแนน",
                        30,
                        "แลกบัตรเติมเงิน AIS 1-2-Call 30 คะแนน แลกรับบัตรเติมเงินในระบบ AIS 1-2-Call มูลค่า 20 บาท",
                        R.drawable.redeem_ais),
                Model.RedeemPrize("แลกคะแนนกับ True 40 คะแนน",
                        40,
                        "แลกบัตรเติมเงิน True Move-H 40 คะแนน แลกรับบัตรเติมเงินในระบบ True Move-H มูลค่า 20 บาท",
                        R.drawable.redeem_truemove),
                Model.RedeemPrize("แลกคะแนนกับ Dtac 50 คะแนน",
                        50,
                        "แลกบัตรเติมเงิน Dtac Happy 50 คะแนน แลกรับบัตรเติมเงินในระบบ Dtac มูลค่า 20 บาท",
                        R.drawable.redeem_dtac)
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
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_redeem, parent, false)
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

        fun setModel(model: Model.RedeemPrize) {
            redeemViewGroup.setModel(model)
        }

    }

    interface RedeemClickInterface {
        fun onClick(position: Int, model: Model.RedeemPrize)
    }
}