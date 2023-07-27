package com.hidenobi.fragmentexercise.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding
import com.hidenobi.fragmentexercise.model.Exercise

private const val EXERCISE_PARAM = "exercise"

class ExerciseFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(exercise: Exercise) =
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    val exerciseJson = Gson().toJson(exercise)
                    putString(EXERCISE_PARAM, exerciseJson)
                }
            }
    }

    private var _binding: FragmentExerciseBinding? = null
    private val binding
        get() = _binding!!

    private var exercise: Exercise? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val exerciseJson = it.getString(EXERCISE_PARAM)
            exercise = Gson().fromJson(exerciseJson, Exercise::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(layoutInflater, container, false)
        initBasedOnType()
        initBehaviour()
        return binding.root
    }

    private fun initBasedOnType() {
        when (exercise?.type) {
            null -> {
                Toast.makeText(context, "Có lỗi trong quá trình chọn bài tập", Toast.LENGTH_SHORT)
                    .show()
                parentFragmentManager.popBackStack("start_exercise", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            Exercise.Type.Walk -> {
                binding.root.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.walk_background, null)
                binding.exerciseTypeTxt.text = resources.getString(R.string.walk)
            }
            Exercise.Type.Run -> {
                binding.root.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.run_background, null)
                binding.exerciseTypeTxt.text = resources.getString(R.string.run)
            }
        }
    }

    private fun initBehaviour() {
        binding.exerciseLockBtn.setOnClickListener {
            Toast.makeText(context, "Chưa hỗ trợ tính năng này", Toast.LENGTH_SHORT).show()
        }

        binding.exerciseLocationBtn.setOnClickListener {
            Toast.makeText(context, "Chưa hỗ trợ tính năng này", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}