package com.hidenobi.fragmentexercise

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetTimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetTimeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSetTimeBinding

    private var exerciseType : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSetTimeBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setFragmentResultListener("requestKey"){requestKey: String, bundle: Bundle ->
            exerciseType = bundle.getInt("Type")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetTimeBinding.bind(view)

        val fragmentManager = requireActivity().supportFragmentManager
        binding.backButton.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StartExerciseFragment())
                .commit()
        }

        binding.startButton.setOnClickListener {
            when(exerciseType){
                1 -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ExerciseRunFragment())
                        .commit()
                }
                2 -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ExerciseWalkFragment())
                        .commit()
                }
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SetTimeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SetTimeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}