package com.github.mag0716.multipledevicesupportsample.ui.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.github.mag0716.multipledevicesupportsample.R
import com.github.mag0716.multipledevicesupportsample.ui.base.LoggingFragment

class PreviewFragment : LoggingFragment() {

    val args: PreviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_preview,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.preview_image).load(args.filePath)
        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            findNavController().navigate(R.id.popToDetail)
        }
    }
}