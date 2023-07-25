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
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseFragmentBinding


class ExerciseFragment : Fragment() {

    private lateinit var binding : FragmentExerciseFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgRun.setOnClickListener{
            val fragment = SetTimeFragment.newInstance(Type.RUN)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack("SetTimeFragment")
                .commit()

        }
        binding.imgWalk.setOnClickListener{
            val fragment = SetTimeFragment.newInstance(Type.WALK)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack("SetTimeFragment")
                .commit()
        }
    }



}