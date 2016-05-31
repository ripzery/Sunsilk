package com.socket9.sunsilk.viewgroups

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.socket9.sunsilk.R
import com.socket9.sunsilk.models.Model
import kotlinx.android.synthetic.main.viewgroup_redeem_history.view.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject

class RedeemHistoryViewGroup : FrameLayout {
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
        viewContainer = inflate(context, R.layout.viewgroup_redeem_history, this)
    }

    private fun initInstances() {
        // findViewById here
        cardView.setOnClickListener {
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
    fun setModel(prize: Model.RedeemPrizeHistory) {
        with(prize.redeemPrize) {
            tvTitle.text = title
            tvPoint.text = "$point points"
            tvDescription.text = description
            Glide.with(context).load(imageUrl).crossFade().into(ivCoverImg)
        }

        tvDate.text = "redeemed :  ${prize.getDateText()}"
    }

    fun getClickObservable() : Observable<Int> {
        return rowClickedObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
