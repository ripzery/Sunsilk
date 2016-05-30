package com.socket9.sunsilk.models

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */


object Model{

    val defaultPlaceHolder = "https://placeholdit.imgix.net/~text?txtsize=33&txt=350×150&w=350&h=150"

    @PaperParcel
    data class RedeemPrize(val title: String, val point: Int, val description: String, var imageUrl: String = defaultPlaceHolder): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(RedeemPrize::class.java)
        }
    }
}