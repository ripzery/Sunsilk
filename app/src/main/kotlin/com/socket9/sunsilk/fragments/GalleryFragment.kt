package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.adapter.GalleryAdapter
import com.socket9.sunsilk.models.Model
import com.socket9.thetsl.extensions.toast
import kotlinx.android.synthetic.main.fragment_redeem_point.*


/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class GalleryFragment : Fragment(), GalleryAdapter.GalleryClickInterface {
    /** Variable zone **/
    lateinit var param1: String
    val galleryList: MutableList<Model.Gallery> = mutableListOf(
            Model.Gallery(10, "ปลดล็อกซัมติง", R.drawable.gallery_video_1),
            Model.Gallery(20, "ปลดล็อกซัมติง", R.drawable.gallery_video_2),
            Model.Gallery(30, "ปลดล็อกซัมติง", R.drawable.gallery_video_3),
            Model.Gallery(30, "ปลดล็อกซัมติง", R.drawable.gallery_video_4)
    )


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): GalleryFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val galleryFragment: GalleryFragment = GalleryFragment()
            galleryFragment.arguments = bundle
            return galleryFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_gallery, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    override fun onClick(position: Int, model: Model.Gallery) {
        toast("$position")
    }


    /** Method zone **/

    private fun initInstance() {
        val layoutManager = LinearLayoutManager(context)
        val galleryAdapter = GalleryAdapter(galleryList, this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = galleryAdapter
    }
}