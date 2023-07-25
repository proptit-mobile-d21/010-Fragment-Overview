package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding

class SetTimeFragment : Fragment() {
    private var _binding: FragmentSetTimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.startBtn.setOnClickListener {
            val exerciseWalkFragment = ExerciseWalkFragment()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainLayout, exerciseWalkFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}