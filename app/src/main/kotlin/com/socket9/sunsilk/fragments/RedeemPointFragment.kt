package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.activities.RedeemDetailActivity
import com.socket9.sunsilk.activities.RedeemHistoryActivity
import com.socket9.sunsilk.adapter.RedeemAdapter
import com.socket9.sunsilk.models.Model
import kotlinx.android.synthetic.main.fragment_redeem_point.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class RedeemPointFragment : Fragment(), RedeemAdapter.RedeemClickInterface {
    /** Variable zone **/
    lateinit var param1: String


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): RedeemPointFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val redeemPointFragment: RedeemPointFragment = RedeemPointFragment()
            redeemPointFragment.arguments = bundle
            return redeemPointFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_redeem_point, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RedeemAdapter.newInstance(listener = this)
    }

    override fun onClick(position: Int, model: Model.RedeemPrize) {
        startActivity<RedeemDetailActivity>(RedeemDetailActivity.INTENT_KEY_PARAM1 to model)
    }
}