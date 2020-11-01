package com.github.mag0716.multipledevicesupportsample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mag0716.multipledevicesupportsample.R
import com.github.mag0716.multipledevicesupportsample.ui.base.LoggingFragment

class ListFragment : LoggingFragment() {

    private val viewModel: ListViewModel by viewModels()

    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        if (view is RecyclerView) {
            adapter = ItemAdapter(emptyList()) {
                findNavController().navigate(ListFragmentDirections.navigateToDetail(it))
            }
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@ListFragment.adapter
            }
        } else {
            throw RuntimeException("must implements RecyclerView")
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }
}