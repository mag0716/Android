package com.github.mag0716.multipledevicesupportsample.ui.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber

open class LoggingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${this::class.java}#onCreate ${savedInstanceState == null}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("${this::class.java}#onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${this::class.java}#onViewCreated")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("${this::class.java}#onAttach")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("${this::class.java}#onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("${this::class.java}#onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("${this::class.java}#onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("${this::class.java}#onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("${this::class.java}#onDestoryView")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("${this::class.java}#onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${this::class.java}#onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("${this::class.java}#onSaveInstanceState")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.d("${this::class.java}#onConfigurationChanged $newConfig")
    }
}