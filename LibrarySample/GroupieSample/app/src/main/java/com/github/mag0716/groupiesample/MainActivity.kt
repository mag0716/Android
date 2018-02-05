package com.github.mag0716.groupiesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.mag0716.groupiesample.databinding.HeaderBinding
import com.github.mag0716.groupiesample.databinding.ItemBinding
import com.xwray.groupie.*
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
         * Items
         */

        /**
         * Groups
         *   Section
         *   NestedGroup
         *   ExpandableGroup
         */
        Section(HeaderItem(Header("Section"))).apply {
            for (index in 1..10) {
                add(TextItem(Item("text$index")))
            }
            adapter.add(this)
        }

        ExpandableGroup(ExpandableHeaderItem(Header("ExpandableGroup")), true).apply {
            for (index in 1..10) {
                add(TextItem(Item("text$index")))
            }
            adapter.add(this)
        }
    }

    open class HeaderItem(private val header: Header) : BindableItem<HeaderBinding>() {
        override fun bind(viewBinding: HeaderBinding, position: Int) {
            viewBinding.header = header
        }

        override fun getLayout() = R.layout.header
    }

    class ExpandableHeaderItem(private val header: Header) : HeaderItem(header), ExpandableItem {

        private lateinit var expandableGroup: ExpandableGroup

        override fun bind(viewBinding: HeaderBinding, position: Int) {
            super.bind(viewBinding, position)
            viewBinding.root.setOnClickListener {
                expandableGroup.onToggleExpanded()
            }
        }

        override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
            expandableGroup = onToggleListener
        }
    }

    class TextItem(private val item: Item) : BindableItem<ItemBinding>() {
        override fun bind(viewBinding: ItemBinding, position: Int) {
            viewBinding.item = item
        }

        override fun getLayout() = R.layout.item
    }
}
