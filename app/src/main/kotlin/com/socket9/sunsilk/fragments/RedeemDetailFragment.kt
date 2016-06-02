package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.socket9.sunsilk.R
import com.socket9.sunsilk.activities.VideoActivity
import com.socket9.sunsilk.extensions.loadingTwoSecThen
import com.socket9.sunsilk.extensions.showDialog
import com.socket9.sunsilk.managers.SharePref
import com.socket9.sunsilk.models.Model
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_redeem_detail.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.share
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class RedeemDetailFragment : Fragment() {

    /** Variable zone **/
    lateinit var model: Model.RedeemPrize
    var isFromHistory: Boolean = false
    val shareLink = "https://youtu.be/Rl-0SBZQeMg"


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(param1: Model.RedeemPrize, fromHistory: Boolean): RedeemDetailFragment {
            var bundle: Bundle = Bundle()
            bundle.putParcelable(ARG_1, param1)
            bundle.putBoolean(ARG_2, fromHistory)
            val redeemDetailFragment: RedeemDetailFragment = RedeemDetailFragment()
            redeemDetailFragment.arguments = bundle
            return redeemDetailFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            model = arguments.getParcelable(ARG_1)
            isFromHistory = arguments.getBoolean(ARG_2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_redeem_detail, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()

        initListener()
    }

    /** Method zone **/

    private fun initInstance() {
        with(model) {
            tvPrizeTitle.text = title
            tvPrizePoint.text = "$point points"
            tvDescription.text = description
            Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivPrize)

            shouldRedeemable(point)
        }
    }

    private fun initListener() {
        btnRedeem.onClick { redeem(model) }
        btnTellFriend.onClick { share("Redeem ${model.title} for only ${model.point} points until 31/06/2016! You may also interest in this video $shareLink", "New redeem prize from Sunsilk!") }
    }

    private fun redeem(model: Model.RedeemPrize) {
        var point = SharePref.getPoint()

        if (point < model.point) {
            showDialog("คะแนนไม่พอ", "คุณต้องการอีก ${model.point - point} คะแนนในการแลกรับของรางวัล")
        } else {
            point -= model.point
            loadingTwoSecThen() {
                val redeemModel: Model.RedeemPrizeHistory = Model.RedeemPrizeHistory(model, Date())
                SharePref.saveRedeemHistory(redeemModel)
                showRedeemSuccessDialog(point)
                SharePref.decreasePointTo(point)
                shouldRedeemable(model.point)
            }
        }

    }

    private fun showRedeemSuccessDialog(point: Int){
        val dialogView = activity.layoutInflater.inflate(R.layout.layout_dialog_redeem_success, rootContainer, false)
        val dialog = alert {
            customView(dialogView)
        }

        val btnOk = dialogView.find<Button>(R.id.btnOk)
        val tvDetail = dialogView.find<TextView>(R.id.tvDetail)

        tvDetail.text = "${tvDetail.text.toString()} คุณมีคะแนนเหลือ $point คะแนน"

        dialog.show()

        btnOk.onClick {
            dialog.dismiss()
        }
    }

    private fun shouldRedeemable(point: Int) {
        val isRedeemable = SharePref.getPoint() < point
        if (isRedeemable) {
            btnRedeem.isEnabled = false
            tvRedeemPointWarning.visibility = View.VISIBLE
            tvRedeemPointWarning.text = "คุณต้องการอีก ${point - SharePref.getPoint()} คะแนนในการแลกรับของรางวัล"
        }

        if (isFromHistory) {
            btnRedeem.visibility = View.GONE
        }
    }
}