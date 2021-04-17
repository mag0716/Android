package com.github.mag0716.localelistsample

import android.content.Context
import android.util.AttributeSet

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        setText(R.string.custom_view_text)
    }
}