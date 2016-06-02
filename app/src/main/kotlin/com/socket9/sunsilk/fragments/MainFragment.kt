package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.sunsilk.R
import com.socket9.sunsilk.activities.RedeemHistoryActivity
import com.socket9.sunsilk.managers.SharePref
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_redeem_point.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class MainFragment : Fragment() {

    /** Variable zone **/
    private var point: Int = 0
    private var pointCumulative: Int = 0


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(param1: Int, param2: Int): MainFragment {
            var bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, param1)
            bundle.putInt(ARG_2, param2)
            val templateFragment: MainFragment = MainFragment()
            templateFragment.arguments = bundle
            return templateFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            point = arguments.getInt(ARG_1)
            pointCumulative = arguments.getInt(ARG_2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_main, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    /** Method zone **/

    private fun initInstance() {
        tvPoint.text = "$point"
        tvPointCumulative.text = "$pointCumulative"

        tvHistory.onClick {
            startActivity<RedeemHistoryActivity>()
        }
//        Glide.with(this).load("http://nationalave.com/wp-content/uploads/2016/05/hodor.jpg").centerCrop().into(civProfileImage)
    }

    fun updatePoint() {
        point = SharePref.getPoint()
        pointCumulative = SharePref.getPointCumulative()
        tvPoint.text = "$point"
        tvPointCumulative.text = "$pointCumulative"
    }
}