package com.socket9.sunsilk.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import com.socket9.sunsilk.fragments.ContentFragment
import com.socket9.sunsilk.fragments.MainFragment
import com.socket9.sunsilk.fragments.RedeemPointFragment
import com.socket9.sunsilk.fragments.ScanBarcodeFragment
import com.socket9.sunsilk.managers.SharePref
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan
import uk.co.chrisjenx.calligraphy.TypefaceUtils

/**
 * Created by Euro (ripzery@gmail.com) on 5/27/16 AD.
 */


class MainTabAdapter : FragmentPagerAdapter {
    private val POSITION_ONE = 0;
    private val POSITION_TWO = 1;
    private val POSITION_THREE = 2;
    private val POSITION_FOUR = 3;
    private var currentPoint = 0
    lateinit private var context:Context
    lateinit private var mainFragment: MainFragment
    lateinit private var scanBarcodeFragment: ScanBarcodeFragment
    lateinit private var redeemPointFragment: RedeemPointFragment
    lateinit  var contentFragment: ContentFragment

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {

    }

    constructor(fragmentManager: FragmentManager, context: Context): super(fragmentManager){
        this.context = context
    }

    companion object {
        val TAB_SIZE = 4
        val TAB_TITLES = listOf("หน้าหลัก", "สแกนโค้ด", "แลกรางวัล", "บทความ")
    }

    override fun getItem(position: Int): Fragment? {
        when(position){
            POSITION_ONE -> return MainFragment.newInstance(SharePref.getPoint(), SharePref.getPointCumulative())
            POSITION_TWO -> return ScanBarcodeFragment.newInstance("")
            POSITION_THREE -> return RedeemPointFragment.newInstance("")
            POSITION_FOUR -> return ContentFragment.newInstance("")
            else -> return null
        }
    }

    override fun getCount(): Int {
        return TAB_SIZE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = TAB_TITLES[position]
        val typefaceSpan = CalligraphyTypefaceSpan(TypefaceUtils.load(context.assets, "fonts/adman/db_adman_x.ttf"))
        val s = SpannableStringBuilder()
        s.append(title);
        s.setSpan(typefaceSpan, 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return SpannableString.valueOf(s);
    }

}