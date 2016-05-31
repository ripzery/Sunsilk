package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.socket9.sunsilk.R
import com.socket9.sunsilk.managers.SharePref
import com.socket9.sunsilk.models.Model
import com.socket9.thetsl.extensions.loadingTwoSecThen
import com.socket9.thetsl.extensions.showDialog
import kotlinx.android.synthetic.main.fragment_redeem_detail.*
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class RedeemDetailFragment : Fragment() {

    /** Variable zone **/
    lateinit var model: Model.RedeemPrize


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: Model.RedeemPrize): RedeemDetailFragment {
            var bundle: Bundle = Bundle()
            bundle.putParcelable(ARG_1, param1)
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
            tvPrizePoint.text = "$point"
            tvDescription.text = description
            Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivPrize)

            val redeemHistory = Model.RedeemPrizeHistory(model, Date())
            if (SharePref.getRedeemHistory().hasPrizeHistoryFrom(redeemHistory)) {
                btnRedeem.isEnabled = false
            }
        }
    }

    private fun initListener() {
        btnRedeem.onClick { redeem(model) }
    }

    private fun redeem(model: Model.RedeemPrize) {
        loadingTwoSecThen() {
            val redeemModel: Model.RedeemPrizeHistory = Model.RedeemPrizeHistory(model, Date())
            SharePref.saveRedeemHistory(redeemModel)
            showDialog("Congratulation", "The prize will be sent to your account soon")
            btnRedeem.isEnabled = false
        }
    }
}