package com.github.mag0716.androidktxsample

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.text)
        textView.text = generateSpannableStringBuilder()
    }

    private fun generateSpannableStringBuilder(): SpannableStringBuilder {
        return SpannableStringBuilder()
                .append("normal\n")
                .append("bold\n", StyleSpan(BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                .append("italic\n", StyleSpan(ITALIC), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                .append("underline\n", UnderlineSpan(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                .append("text color\n", ForegroundColorSpan(Color.RED), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                .append("background color\n", BackgroundColorSpan(Color.RED), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                .append("custom text appearance\n", TextAppearanceSpan(this, R.style.CustomTextAppearance), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}
