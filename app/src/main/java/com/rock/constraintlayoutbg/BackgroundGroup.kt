package com.rock.constraintlayoutbg

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

class BackgroundGroup(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    ConstraintHelper(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    init {
        mUseViewMeasure = true
    }

    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)

        var left = -1
        var right = -1
        var top = -1
        var bottom = -1
        referencedIds.forEach {

            val child = container?.getViewById(it)
            val childLeft = child?.left ?: 0
            val childRight = child?.right ?: 0
            val childTop = child?.top ?: 0
            val childBottom = child?.bottom ?: 0
            left = if (left < 0 || left > childLeft) childLeft else left
            right = if (right < 0 || right < childRight) childRight else right
            top = if (top < 0 || top > childTop) childTop else top
            bottom = if (bottom < 0 || bottom < childBottom) childBottom else bottom
        }

        layout(
            left - paddingLeft, top - paddingTop
            , right + paddingRight, bottom + paddingBottom
        )

    }

}