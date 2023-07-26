package com.hidenobi.fragmentexercise.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.FragmentStartExerciseBinding
import com.hidenobi.fragmentexercise.model.Exercise

typealias StartExerciseOnClick = (type: Exercise.Type) -> Unit

class StartExerciseFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(exerciseOnClick: StartExerciseOnClick) =
            StartExerciseFragment().apply {
                this.exerciseOnClick = exerciseOnClick
            }
    }

    private var _binding: FragmentStartExerciseBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var exerciseOnClick: StartExerciseOnClick

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartExerciseBinding.inflate(inflater, container, false)
        initBehaviour()
        return binding.root
    }

    private fun initBehaviour() {
        binding.startExerciseRunBtn.setOnClickListener { exerciseOnClick.invoke(Exercise.Type.Run) }
        binding.startExerciseWalkBtn.setOnClickListener { exerciseOnClick.invoke(Exercise.Type.Walk) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}