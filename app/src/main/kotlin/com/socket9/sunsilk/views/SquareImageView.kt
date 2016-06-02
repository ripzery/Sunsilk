package com.socket9.sunsilk.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Euro on 3/16/16 AD.
 */
class SquareImageView : ImageView {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val height = measuredHeight
        setMeasuredDimension(height, height)
    }
}