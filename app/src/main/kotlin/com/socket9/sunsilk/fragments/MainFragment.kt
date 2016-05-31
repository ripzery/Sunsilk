package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.sunsilk.R
import com.socket9.sunsilk.managers.SharePref
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class MainFragment : Fragment() {

    /** Variable zone **/
    private var point: Int = 0


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: Int): MainFragment {
            var bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, param1)
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
        Glide.with(this).load("http://nationalave.com/wp-content/uploads/2016/05/hodor.jpg").centerCrop().into(civProfileImage)
    }

    fun updatePoint() {
        point = SharePref.getPoint()
        tvPoint.text = "$point"
    }
}