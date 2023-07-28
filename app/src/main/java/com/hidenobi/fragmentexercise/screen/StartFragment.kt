package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding
import com.hidenobi.fragmentexercise.modelView.FragmentViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private lateinit var btnToRun: LinearLayout
    private lateinit var btnToWalk: LinearLayout
    private val viewModel: FragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        initComponent()
        setupClickListener()
        return binding.root
    }

    private fun setupClickListener() {
        val setTimeFragment = SetTimeFragment()
        btnToWalk.setOnClickListener {
            viewModel.setExcerciseType(1)
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, setTimeFragment)
                addToBackStack(null)
                commit()
            }
        }
        btnToRun.setOnClickListener {
            viewModel.setExcerciseType(2)
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, setTimeFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun initComponent() {
        btnToRun = binding.llRun
        btnToWalk = binding.llWalk
    }


}