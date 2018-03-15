package com.github.mag0716.popupwindowsample

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.PopupWindow
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var popup: PopupWindow
    private lateinit var listPopup: ListPopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(container)
                constraintSet.setVerticalBias(R.id.popupButton, progress.toFloat() / 100.0f)
                constraintSet.applyTo(container)
            }

        })

        popup = PopupWindow(
                LayoutInflater.from(this).inflate(R.layout.view_popup, findViewById(R.id.container), false),
                resources.getDimensionPixelOffset(R.dimen.popup_width),
                resources.getDimensionPixelOffset(R.dimen.popup_height))
        listPopup = ListPopupWindow(this)
        listPopup.setAdapter(ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.items)
        ))
        val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.items,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        popup.dismiss()
        listPopup.dismiss()
    }

    fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.popupButton -> {
                if (popup.isShowing) {
                    popup.dismiss()
                } else {
                    popup.showAsDropDown(view)
                }
            }
            R.id.listPopupButton -> {
                listPopup.anchorView = view
                listPopup.show()
            }
        }

    }
}
