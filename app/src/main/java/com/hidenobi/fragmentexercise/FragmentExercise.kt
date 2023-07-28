package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding


class FragmentExercise : Fragment() {

    private lateinit var view: View
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        view = binding.root
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("data"){ requestKey, bundle ->
            val result = bundle.getString("key")
            binding.maintext.text = result
            if(result=="Run"){
                binding.root.setBackgroundResource(R.color.bg_run)
            } else{
                binding.root.setBackgroundResource(R.color.bg_walk)

            }
        }
    }


}