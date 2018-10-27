package com.github.mag0716.multiplemodulesample.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.mag0716.api.ApiClientFactory
import com.github.mag0716.api.model.Data
import com.github.mag0716.multiplemodulesample.R
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

class ListFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job

    // TODO: datasource でやる
    private val apiService = ApiClientFactory(this).create()
    private lateinit var adapter: Adapter

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

        adapter = Adapter(requireContext())
        if (savedInstanceState == null) {
            fetchData()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.list.adapter = adapter
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun fetchData() = launch(Dispatchers.Unconfined) {
        val dataList = apiService.data().await()
        adapter.addData(dataList)
    }

    private class Adapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        val dataList: MutableList<Data> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                // TODO: 画面遷移処理
            }
            holder.titleText.text = dataList[position].title
        }

        fun addData(dataList: List<Data>) {
            this.dataList.addAll(dataList)
            notifyDataSetChanged()
        }
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.titleText
    }
}