package com.github.mag0716.popupwindowsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow

class MainActivity : AppCompatActivity() {

    private lateinit var popup: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popup = PopupWindow(
                LayoutInflater.from(this).inflate(R.layout.view_popup, findViewById(R.id.container), false),
                resources.getDimensionPixelOffset(R.dimen.popup_width),
                resources.getDimensionPixelOffset(R.dimen.popup_height))
    }

    override fun onPause() {
        super.onPause()
        popup.dismiss()
    }

    fun onClick(view: View) {
        if (popup.isShowing) {
            popup.dismiss()
        } else {
            popup.showAsDropDown(view)
        }
    }
}
