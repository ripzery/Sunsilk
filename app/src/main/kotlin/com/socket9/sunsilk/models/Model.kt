package com.socket9.sunsilk.models

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder
import java.util.*

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

    @PaperParcel
    data class RedeemPrizeList(var modelList: MutableList<RedeemPrize>): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(RedeemPrizeList::class.java)
        }
    }

    @PaperParcel
    data class RedeemPrizeHistory(val redeemPrize: RedeemPrize, val date: Date): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(RedeemPrizeHistory::class.java)
        }

        fun getDateText(): String{
            val dateTime: DateTime = DateTime(date)
            val fmt: DateTimeFormatter = DateTimeFormat.forPattern("EEE dd MM yyyy HH:mm")
            return dateTime.toString(fmt)
        }
    }

    @PaperParcel
    data class RedeemPrizeHistoryList(val modelList: MutableList<RedeemPrizeHistory>): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(RedeemPrizeHistoryList::class.java)
        }

        fun hasPrizeHistoryFrom(prizeHistory: RedeemPrizeHistory): Boolean = modelList.any {
            it.redeemPrize.title.equals(prizeHistory.redeemPrize.title)
        }
    }

    @PaperParcel
    data class Article(val title:String, val subtitle:String, val img:String = "https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg"): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(Article::class.java)
        }
    }

    @PaperParcel
    data class ArticlesList(val modelList: MutableList<Article>): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(ArticlesList::class.java)
        }
    }

    @PaperParcel
    data class Gallery(val point:Int, val description:String,val imageUrl:Int = 0): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(Gallery::class.java)
        }
    }

    @PaperParcel
    data class GalleryList(val modelList: MutableList<Gallery>): PaperParcelable{
        companion object {
            @JvmField val CREATOR = PaperParcelable.Creator(GalleryList::class.java)
        }
    }

}