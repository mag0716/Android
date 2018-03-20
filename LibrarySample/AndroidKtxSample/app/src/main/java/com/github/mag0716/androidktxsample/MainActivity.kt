package com.github.mag0716.androidktxsample

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannedString
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import android.widget.TextView
import androidx.text.*
import androidx.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)
        textView.text = generateSpannableStringBuilder()
        textView2 = findViewById(R.id.text2)
        textView2.text = generateSpannableWithOperator()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
                textView.isVisible = !textView.isVisible
            }
        }
    }

    private fun generateSpannableStringBuilder(): SpannedString {
        return buildSpannedString {
            append("normal\n")
            bold {
                append("bold\n")
            }
            italic {
                append("italic\n")
            }
            underline {
                append("underline\n")
            }
            color(Color.RED) {
                append("text color\n")
            }
            backgroundColor(Color.RED) {
                append("background color\n")
            }
            inSpans(TextAppearanceSpan(this@MainActivity, R.style.CustomTextAppearance)) {
                append("custom text appearance\n")
            }
        }
    }

    private fun generateSpannableWithOperator(): Spannable {
        val spannable = "SpannableString with + operator".toSpannable()
        spannable += StyleSpan(BOLD)
        return spannable
    }
}
