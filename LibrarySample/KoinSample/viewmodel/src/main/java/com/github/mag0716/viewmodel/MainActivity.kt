package com.github.mag0716.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ViewModel"
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "${mainViewModel.hello}($mainViewModel)")

        val directMainViewModel = getViewModel<MainViewModel>()
        Log.d(TAG, "${directMainViewModel.hello}($directMainViewModel)")
    }
}
