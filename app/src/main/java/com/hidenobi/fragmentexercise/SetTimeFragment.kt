package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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


        val data = arguments?.getString("action").toString()
        val exerciseFragment = ExerciseFragment()
        val bundle = Bundle()

        binding.start.setOnClickListener {
            bundle.putString("action2","$data")
            exerciseFragment.arguments = bundle
            
            requireFragmentManager().beginTransaction()
                .replace(binding.fragmentSetTime.id, exerciseFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun calculateDifference() {
        val startTimeStr = binding.startTime.text.toString()
        val endTimeStr = binding.endTime.text.toString()

        // Convert time strings to Date objects using SimpleDateFormat
        val timeFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        val startTime: Date = timeFormat.parse(startTimeStr) ?: return
        val endTime: Date = timeFormat.parse(endTimeStr) ?: return

        // Calculate the time difference in milliseconds
        val timeDifferenceMillis = endTime.time - startTime.time

        // Convert milliseconds to minutes and seconds
        val minutes = timeDifferenceMillis / (60 * 1000) % 60
        val seconds = timeDifferenceMillis / 1000 % 60

        // Display the result (you can customize how to display or use the result)
        val result = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        //Log.d("Trung","$result")

    }

}