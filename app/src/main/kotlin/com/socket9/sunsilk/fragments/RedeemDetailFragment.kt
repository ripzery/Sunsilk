package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.sunsilk.R
import com.socket9.sunsilk.models.Model
import kotlinx.android.synthetic.main.fragment_redeem_detail.*

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
    }

    /** Method zone **/

    private fun initInstance() {
        with(model){
            tvPrizeTitle.text = title
            tvPrizePoint.text = "$point"
            tvDescription.text = description
            Glide.with(context).load(imageUrl).into(ivPrize)
        }
    }
}