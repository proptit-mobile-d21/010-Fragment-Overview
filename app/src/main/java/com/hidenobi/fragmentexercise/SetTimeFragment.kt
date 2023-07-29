package com.hidenobi.fragmentexercise

import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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

        binding.startEditText.setOnClickListener{
            val c = Calendar.getInstance()

            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { view, hourOfDay, minute ->
                    binding.startEditText.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        binding.endEditText.setOnClickListener{
            val c = Calendar.getInstance()

            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { view, hourOfDay, minute ->
                    binding.endEditText.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        binding.startButton.setOnClickListener {
            if(binding.startEditText.text.isNullOrEmpty() || binding.startEditText.text.isNullOrEmpty()){
                Toast.makeText(context, "Start Time/End Time is empty", Toast.LENGTH_LONG).show()
            }
            else{
                val time1 = binding.startEditText.text.toString()
                val unit1 = time1.split(":")
                val time2 = binding.endEditText.text.toString()
                val unit2 = time2.split(":")
                if(checkTimeInput(unit1[0].toLong(), unit1[1].toLong(), unit2[0].toLong(), unit2[1].toLong())){
                    val hour = unit2[0].toLong() - unit1[0].toLong()
                    val min = unit2[1].toLong() - unit1[1].toLong()
                    setFragmentResult("time_left", bundleOf("hour" to hour, "min" to min))
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
                else{
                    Toast.makeText(context, "Error Input!!!", Toast.LENGTH_LONG).show();
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

    private fun checkTimeInput(hour1 : Long, min1 : Long, hour2 : Long, min2 : Long) : Boolean{
        if(hour1 > hour2) return false
        if(hour1 == hour2){
            if(min1 > min2) return false
        }
        return true
    }
}