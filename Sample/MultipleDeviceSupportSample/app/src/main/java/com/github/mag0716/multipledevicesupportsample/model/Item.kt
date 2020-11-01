package com.github.mag0716.multipledevicesupportsample.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Item(
    val id: String,
    val content: String,
    val details: String
) : Serializable {
    companion object {
        fun createItems(count: Int): List<Item> {
            val list = mutableListOf<Item>()
            for (i in 1..count) {
                list.add(createItem(i))
            }
            return list
        }

        fun createItem(position: Int): Item {
            return Item(
                position.toString(),
                "Item $position",
                makeDetails(position)
            )
        }

        private fun makeDetails(position: Int): String {
            val builder = StringBuilder()
            builder.append("Details about Item: ").append(position)
            for (i in 0 until position) {
                builder.append("\nMore details information here.")
            }
            return builder.toString()
        }
    }
}