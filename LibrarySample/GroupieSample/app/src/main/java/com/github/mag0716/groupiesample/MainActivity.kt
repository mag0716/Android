package com.github.mag0716.groupiesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.mag0716.groupiesample.databinding.HeaderBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainActivity : AppCompatActivity() {

    lateinit var list: RecyclerView
    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.list)
        list.layoutManager = GridLayoutManager(this, adapter.spanCount)
        list.adapter = adapter
        createItems()
    }

    private fun createItems() {
        /**
         * Groups
         *   Section
         *   NestedGroup
         *   ExpandableGroup
         */

        /**
         * Items
         */

        adapter.add(HeaderItem(Header("header1")))
    }

    class HeaderItem constructor(private val header: Header) : BindableItem<HeaderBinding>() {
        override fun bind(viewBinding: HeaderBinding, position: Int) {
            viewBinding.header = header
        }

        override fun getLayout() = R.layout.header

    }
}
