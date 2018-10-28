package com.github.mag0716.multiplemodulesample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mag0716.datasource.Repository
import com.github.mag0716.multiplemodulesample.R
import com.github.mag0716.usercase.GetDataDetailUseCase
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

class DetailFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // TODO: app では :datasource には依存させずに、inject する
    private val getDataDetailUseCase = GetDataDetailUseCase(
            Repository(this)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = DetailFragmentArgs.fromBundle(arguments).id
        fetchDataDetail(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun fetchDataDetail(id: Int) = launch {
        val detail = getDataDetailUseCase.execute(id)
        titleText.text = detail.title

        // TODO: ProgressBar, エラー処理
    }
}