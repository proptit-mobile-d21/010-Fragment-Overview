package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding

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
            select()
        }
        binding.walkBtn.setOnClickListener {
            select()
        }
    }

    private fun select() {
        val setTimeFragment = SetTimeFragment()
        parentFragmentManager
            .beginTransaction()
            .add(R.id.mainLayout, setTimeFragment)
            .addToBackStack(null)
            .commit()
    }
}