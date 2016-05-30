package com.socket9.sunsilk.models

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */


object Model{

    @PaperParcel
    data class RedeemPrize(val title: String, val point: Int, val description: String): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(RedeemPrize::class.java)
        }
    }
}