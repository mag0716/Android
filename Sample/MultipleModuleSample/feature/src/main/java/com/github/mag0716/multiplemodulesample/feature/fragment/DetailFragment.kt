package com.github.mag0716.multiplemodulesample.feature.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mag0716.multiplemodulesample.App
import com.github.mag0716.multiplemodulesample.feature.R
import com.github.mag0716.usercase.IGetDataDetailUseCase
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var getDataDetailUseCase: IGetDataDetailUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

        getDataDetailUseCase = (requireActivity().application as App).getDataDetailUseCase
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