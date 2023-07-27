package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding
import com.hidenobi.fragmentexercise.model.Time

class ExerciseFragment : Fragment() {
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    private lateinit var exercise: Exercise
    private lateinit var counter: Time

    companion object {
        fun newInstance(exercise: Exercise, counter: Time): Fragment {
            return ExerciseFragment().apply {
                this.exercise = exercise
                this.counter = counter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        when (exercise) {
            Exercise.RUN -> {
                binding.fragmentExercise.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.orange_gradient, null)
                binding.mode.text = getString(R.string.run)
            }

            Exercise.WALK -> {
                binding.fragmentExercise.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.blue_gradient, null)
                binding.mode.text = getString(R.string.walk)
            }
        }
        binding.timeLeftText.text = "${getString(R.string.time_left)} - $counter"
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}