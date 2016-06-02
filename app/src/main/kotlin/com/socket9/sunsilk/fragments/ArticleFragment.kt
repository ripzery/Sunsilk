package com.socket9.sunsilk.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.adapter.ArticlesAdapter
import com.socket9.sunsilk.models.Model
import com.socket9.thetsl.extensions.toast
import kotlinx.android.synthetic.main.fragment_redeem_point.*


/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class ArticleFragment : Fragment(), ArticlesAdapter.ArticleClickInterface {

    /** Variable zone **/
    lateinit var param1: String
    var articlesList: MutableList<Model.Article> = mutableListOf(
            Model.Article("Article 1", "subtitle 1"),
            Model.Article("Article 2", "subtitle 2"),
            Model.Article("Article 3", "subtitle 3")
    )

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): ArticleFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val articleFragment: ArticleFragment = ArticleFragment()
            articleFragment.arguments = bundle
            return articleFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_article, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    override fun onClick(position: Int, model: Model.Article) {
        toast("$position")
    }

    /** Method zone **/

    private fun initInstance() {
        val adapter = ArticlesAdapter.newInstance(articlesList, this)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }
}