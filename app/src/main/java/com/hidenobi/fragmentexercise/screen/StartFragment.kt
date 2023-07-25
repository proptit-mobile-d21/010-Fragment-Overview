package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private lateinit var btnToRun: LinearLayout

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
        btnToRun.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, setTimeFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun initComponent() {
        btnToRun = binding.llRun
    }


}