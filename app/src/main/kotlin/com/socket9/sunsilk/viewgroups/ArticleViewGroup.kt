package com.socket9.sunsilk.viewgroups

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.socket9.sunsilk.R
import com.socket9.sunsilk.models.Model
import kotlinx.android.synthetic.main.viewgroup_article.view.*
import org.jetbrains.anko.onClick
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject

class ArticleViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    private var rowClickedObservable: PublishSubject<Int> = PublishSubject.create()

    /** Override method zone **/
    constructor(context: Context) : super(context) {
        initInflate()
        initInstances()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, defStyleAttr, 0)
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, defStyleAttr, defStyleRes)
    }


    private fun initInflate() {
        viewContainer = inflate(context, R.layout.viewgroup_article, this)
    }

    private fun initInstances() {
        // findViewById here
        layoutContainer.onClick {
            rowClickedObservable.onNext(1)
        }
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    /** Method zone **/

    fun setModel(model: Model.Article) {
        with(model){
            tvTitle.text = title
            tvSubtitle.text = subtitle
            Glide.with(context).load(R.drawable.profile).into(civLogo)
        }
    }

    fun getClickObservable() : Observable<Int> {
        return rowClickedObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
