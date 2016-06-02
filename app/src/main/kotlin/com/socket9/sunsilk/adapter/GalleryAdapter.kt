package com.socket9.sunsilk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.models.Model
import com.socket9.sunsilk.viewgroups.GalleryViewGroup
import com.socket9.sunsilk.viewgroups.RedeemHistoryViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

/**
 * Created by Euro (ripzery@gmail.com) on 5/30/16 AD.
 */

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>, AnkoLogger {

    lateinit var galleryList: MutableList<Model.Gallery>
    private var listener: GalleryAdapter.GalleryClickInterface? = null

    companion object {
        fun newInstance(GalleryList: MutableList<Model.Gallery>, listener: GalleryClickInterface): GalleryAdapter {
            return GalleryAdapter(GalleryList, listener)
        }
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.setModel(galleryList[position])
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GalleryViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_gallery, parent, false)
        return GalleryViewHolder(view)
    }


    constructor(GalleryList: MutableList<Model.Gallery>, listener: GalleryClickInterface) {
        this.galleryList = GalleryList
        this.listener = listener
    }

    inner class GalleryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var galleryViewGroup: GalleryViewGroup;

        init {
            // find itemView here
            galleryViewGroup = itemView!!.find<GalleryViewGroup>(R.id.galleryViewGroup)

            galleryViewGroup.getClickObservable().subscribe {
                listener?.onClick(adapterPosition, galleryList[adapterPosition])
            }
        }

        fun setModel(model: Model.Gallery) {
            galleryViewGroup.setModel(model)
        }

    }

    interface GalleryClickInterface {
        fun onClick(position: Int, model: Model.Gallery)
    }
}