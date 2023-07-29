package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding:FragmentStartBinding
    private lateinit var walkBtn:Button
    private lateinit var runBtn:Button
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater,container,false)
        val bundle = Bundle()
        initComponent()
        setUpClickListener()
        return binding.root
    }
    private fun setUpClickListener() {
        walkBtn.setOnClickListener(){
            val fragment: Fragment = SetTimeFragment()
            bundle.putString("choose","Walk")
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, fragment)
                addToBackStack(null)
                commit()
            }
        }
        runBtn.setOnClickListener(){
            val fragment: Fragment = SetTimeFragment()
            bundle.putString("choose","Run")
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun initComponent() {
        walkBtn = binding.btnWalk
        runBtn = binding.btnRun
        bundle = Bundle()
    }
}