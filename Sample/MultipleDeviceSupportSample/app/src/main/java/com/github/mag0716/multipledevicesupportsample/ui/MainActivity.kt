package com.github.mag0716.multipledevicesupportsample.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.mag0716.multipledevicesupportsample.R
import com.github.mag0716.multipledevicesupportsample.ui.base.LoggingActivity
import com.github.mag0716.multipledevicesupportsample.ui.license.LicenseActivity
import com.github.mag0716.multipledevicesupportsample.ui.settings.SettingsActivity

class MainActivity : LoggingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.license -> {
                startActivity(Intent(this, LicenseActivity::class.java))
            }
        }
        return false
    }
}