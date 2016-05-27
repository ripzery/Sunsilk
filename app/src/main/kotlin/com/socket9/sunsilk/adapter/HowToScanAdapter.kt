package com.socket9.sunsilk.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.socket9.sunsilk.fragments.*

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */


class HowToScanAdapter : FragmentPagerAdapter {
    lateinit private var context:Context
    lateinit private var howToScanFragment: HowToScanFragment

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {

    }

    constructor(fragmentManager: FragmentManager, context: Context): super(fragmentManager){
        this.context = context
        howToScanFragment = HowToScanFragment.newInstance("Step 1")
    }

    companion object {
        val TAB_SIZE = 4
        val STEPS = listOf("หาบาร์โค้ด", "กดปุ่มสแกน", "หันไปหาบาร์โค้ด", "รอแป๊ปนะ")
    }

    override fun getItem(position: Int): Fragment? {
        return HowToScanFragment.newInstance(STEPS[position])
    }

    override fun getCount(): Int {
        return TAB_SIZE
    }
}