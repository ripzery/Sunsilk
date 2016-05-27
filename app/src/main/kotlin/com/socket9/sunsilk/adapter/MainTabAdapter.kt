package com.socket9.sunsilk.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.socket9.sunsilk.fragments.ContentFragment
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.sunsilk.fragments.RedeemPointFragment
import com.socket9.sunsilk.fragments.ScanBarcodeFragment

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */


class MainTabAdapter : FragmentPagerAdapter {
    private val POSITION_ONE = 0;
    private val POSITION_TWO = 1;
    private val POSITION_THREE = 2;
    private val POSITION_FOUR = 3;
    lateinit private var context:Context
    lateinit private var mainFragment: Fragment
    lateinit private var scanBarcodeFragment: ScanBarcodeFragment
    lateinit private var redeemPointFragment: RedeemPointFragment
    lateinit  var contentFragment: ContentFragment

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {

    }

    constructor(fragmentManager: FragmentManager, context: Context): super(fragmentManager){
        this.context = context
        mainFragment = MainFragment.newInstance("")
        scanBarcodeFragment = ScanBarcodeFragment.newInstance("")
        redeemPointFragment = RedeemPointFragment.newInstance("")
        contentFragment = ContentFragment.newInstance("")
    }

    companion object {
        val TAB_SIZE = 4
        val TAB_TITLES = listOf("MyPoint", "Barcode", "Redeem", "Content")
    }

    override fun getItem(position: Int): Fragment? {
        when(position){
            POSITION_ONE -> return mainFragment
            POSITION_TWO -> return scanBarcodeFragment
            POSITION_THREE -> return redeemPointFragment
            else -> return contentFragment
        }
    }

    override fun getCount(): Int {
        return TAB_SIZE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

}