package com.socket9.sunsilk.fragments

import android.content.ComponentName
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.*
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.adapter.ArticlesAdapter
import com.socket9.sunsilk.models.Model
import kotlinx.android.synthetic.main.fragment_redeem_point.*


/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class ArticleFragment : Fragment(), ArticlesAdapter.ArticleClickInterface {

    /** Variable zone **/
    private var customTabsConnection: CustomTabsServiceConnection? = null
    private var customTabsSession: CustomTabsSession? = null
    lateinit var param1: String
    var articlesList: MutableList<Model.Article> = mutableListOf(
            Model.Article("ฟื้นฟูผมแห้งเสีย", "โทมัส ทอร์", R.drawable.pro_01, "http://www.sunsilkthailand.com/article/detail/765451/thomastaw"),
            Model.Article("ผู้เชี่ยวชาญด้านน้ำหนักของเส้นผม", "จามาล ฮามมาดิ", R.drawable.pro_04, "http://www.sunsilkthailand.com/article/detail/765500/jamal-hammaki"),
            Model.Article("ยืดผมตรง", "ยูโกะ ยามาชิตะ", R.drawable.pro_03, "http://www.sunsilkthailand.com/article/detail/765482/yuko-yamashita"),
            Model.Article("ผมดำเงางาม", "เท็ดดี้ ชาลส์", R.drawable.pro_02, "http://www.sunsilkthailand.com/article/detail/765512/teddycharles")
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
        openUrl(Uri.parse(model.url))
    }

    /** Method zone **/

    private fun openUrl(uri: Uri) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder(customTabsSession)
        builder.setShowTitle(true)
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPink))
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_arrow_back_white_24dp))
        builder.setStartAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left)
        builder.setExitAnimations(activity, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        builder.build().launchUrl(activity, uri)
    }

    private fun connectCustomTabsService() {
        val chromePackageName = "com.android.chrome"

        customTabsConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(p0: ComponentName?, customTabsClient: CustomTabsClient?) {
                // หยุดเชื่อมต่อ Custom Tabs Service
                createCustomTabsSession(customTabsClient!!);
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                // เชื่อมต่อกับ Custom Tabs Service ได้แล้ว
            }

        }

        CustomTabsClient.bindCustomTabsService(activity, chromePackageName, customTabsConnection);
    }

    private fun disconnectCustomTabsService() {
        if (customTabsConnection != null) activity.unbindService(customTabsConnection)
    }

    private fun createCustomTabsSession(customTabsClient: CustomTabsClient) {
        customTabsClient.warmup(0);

        customTabsSession = customTabsClient.newSession(object : CustomTabsCallback() {
            override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                // เมื่อมี Navigation Event ใดๆเกิดขึ้นบน Custom Tabs
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        disconnectCustomTabsService()
    }

    private fun initInstance() {
        connectCustomTabsService()
        val adapter = ArticlesAdapter.newInstance(articlesList, this)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }
}