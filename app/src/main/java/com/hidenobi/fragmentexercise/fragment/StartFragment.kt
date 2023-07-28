package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding
import com.hidenobi.fragmentexercise.model.Exercise

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.runBtn.setOnClickListener {
            select(Exercise.RUN)
        }
        binding.walkBtn.setOnClickListener {
            select(Exercise.WALK)
        }
    }

    private fun select(exercise: Exercise) {
        val setTimeFragment = SetTimeFragment.newInstance(exercise)
        parentFragmentManager
            .beginTransaction()
            .add(R.id.mainLayout, setTimeFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}