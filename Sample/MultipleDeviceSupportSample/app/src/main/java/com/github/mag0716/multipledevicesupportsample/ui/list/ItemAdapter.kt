package com.github.mag0716.multipledevicesupportsample.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mag0716.multipledevicesupportsample.R
import com.github.mag0716.multipledevicesupportsample.model.Item

class ItemAdapter(
    private var values: List<Item>,
    private val onClickItem: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
        holder.view.setOnClickListener {
            onClickItem(item)
        }
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<Item>) {
        values = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}