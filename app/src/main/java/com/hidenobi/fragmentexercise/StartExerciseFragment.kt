package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.hidenobi.fragmentexercise.databinding.FragmentStartExerciseBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartExerciseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentStartExerciseBinding
    private  var exerciseType  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStartExerciseBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStartExerciseBinding.bind(view)

        binding.runButton.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            exerciseType = 1
            setFragmentResult("requestKey", bundleOf("Type" to exerciseType))
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SetTimeFragment())
                .addToBackStack(null) // Optional: Add to back stack to handle back navigation
                .commit()

        }

        binding.walkButton.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            exerciseType = 2
            setFragmentResult("requestKey", bundleOf("Type" to exerciseType))
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SetTimeFragment())
                .addToBackStack(null) // Optional: Add to back stack to handle back navigation
                .commit()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}