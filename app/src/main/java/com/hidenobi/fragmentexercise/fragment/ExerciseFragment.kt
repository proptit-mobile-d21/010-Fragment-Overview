package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.Type


class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageViewRun = view.findViewById<ImageView>(R.id.imgRun)
        val imageViewWalk = view.findViewById<ImageView>(R.id.imgWalk)
        imageViewRun?.setOnClickListener{
            val fragment = SetTimeFragment.newInstance(Type.RUN)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack("SetTimeFragment")
                .commit()

        }
        imageViewWalk?.setOnClickListener{
            val fragment = SetTimeFragment.newInstance(Type.WALK)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack("SetTimeFragment")
                .commit()
        }
    }



}