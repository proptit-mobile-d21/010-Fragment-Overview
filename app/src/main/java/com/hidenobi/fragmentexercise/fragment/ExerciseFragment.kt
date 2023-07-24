package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.hidenobi.fragmentexercise.R
import org.w3c.dom.Text


class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageViewRun = view?.findViewById<ImageView>(R.id.imgRun)

        imageViewRun?.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add<SetTimeFragment>(R.id.fragmentContainerView)
                addToBackStack("SetTimeFragment")
            }
        }
    }



}