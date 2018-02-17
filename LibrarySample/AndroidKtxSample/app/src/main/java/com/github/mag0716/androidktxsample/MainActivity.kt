package com.github.mag0716.androidktxsample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannedString
import android.text.style.TextAppearanceSpan
import android.view.View
import android.widget.TextView
import androidx.text.*

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.text)
        textView.text = generateSpannableStringBuilder()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
                // 0.1 では、isVisible, isInvisible, isGone は利用不可
                textView.visibility = if (textView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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
}
