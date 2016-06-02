package com.socket9.sunsilk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.models.Model
import com.socket9.sunsilk.viewgroups.ArticleViewGroup
import com.socket9.sunsilk.viewgroups.RedeemHistoryViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>, AnkoLogger {

    lateinit var articlesList: MutableList<Model.Article>
    private var listener: ArticlesAdapter.ArticleClickInterface? = null

    companion object {
        fun newInstance(articlesList: MutableList<Model.Article>, listener: ArticleClickInterface): ArticlesAdapter {
            return ArticlesAdapter(articlesList, listener)
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.setModel(articlesList[position])
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_article, parent, false)
        return ArticleViewHolder(view)
    }


    constructor(articlesList: MutableList<Model.Article>, listener: ArticleClickInterface) {
        this.articlesList = articlesList
        this.listener = listener
    }

    inner class ArticleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var redeemViewGroup: ArticleViewGroup;

        init {
            // find itemView here
            redeemViewGroup = itemView!!.find<ArticleViewGroup>(R.id.articleViewGroup)

            redeemViewGroup.getClickObservable().subscribe {
                listener?.onClick(adapterPosition, articlesList[adapterPosition])
            }
        }

        fun setModel(model: Model.Article) {
            redeemViewGroup.setModel(model)
        }

    }

    interface ArticleClickInterface {
        fun onClick(position: Int, model: Model.Article)
    }
}