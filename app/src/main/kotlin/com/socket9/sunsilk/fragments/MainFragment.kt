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
    lateinit var param1: String


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): MainFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
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
            param1 = arguments.getString(ARG_1)
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

    /** Method zone **/

    private fun initInstance() {
        tvPoint.text = "${SharePref.getPoint()}"
        Glide.with(this).load("http://nationalave.com/wp-content/uploads/2016/05/hodor.jpg").centerCrop().into(civProfileImage)
    }

    fun setPoint(point: Int){
        tvPoint.text = "$point"
    }
}