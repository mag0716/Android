package com.github.mag0716.multipledevicesupportsample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mag0716.multipledevicesupportsample.model.Item

class ListViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>(
        Item.createItems(100)
    )
    val items: LiveData<List<Item>> = _items
}