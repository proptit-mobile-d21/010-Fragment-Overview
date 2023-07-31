package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRun.setOnClickListener {
            selectExercise("Run")
        }

        binding.btnWalk.setOnClickListener {
            selectExercise("Walk")
        }
    }

    private fun selectExercise(type: String) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            val setTimeFragment = SetTimeFragment.newInstance(type)
            replace(binding.fragmentStart.id, setTimeFragment)
            addToBackStack(null)
            commit()
        }
    }
}