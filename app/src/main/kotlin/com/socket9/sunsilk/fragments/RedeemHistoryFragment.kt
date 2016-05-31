package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.adapter.RedeemHistoryAdapter
import com.socket9.sunsilk.managers.SharePref
import kotlinx.android.synthetic.main.fragment_redeem_history.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class RedeemHistoryFragment : Fragment() {

    /** Variable zone **/
    lateinit var param1: String


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): RedeemHistoryFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val redeemHistoryFragment: RedeemHistoryFragment = RedeemHistoryFragment()
            redeemHistoryFragment.arguments = bundle
            return redeemHistoryFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_redeem_history, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        val redeemList = SharePref.getRedeemHistory().modelList
        val adapter = RedeemHistoryAdapter(redeemList)
        recyclerView.adapter = adapter

        tvEmpty.visibility = if(redeemList.size > 0) View.GONE else View.VISIBLE
    }
}