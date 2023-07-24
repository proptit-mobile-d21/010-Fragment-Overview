package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.hidenobi.fragmentexercise.R


class SetTimeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_time, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgBack = view?.findViewById<ImageView>(R.id.imgBack)
        imgBack?.setOnClickListener {
            Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

    }


}