package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.SetTimeBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SetTimeFragment : Fragment() {
    private lateinit var binding: SetTimeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SetTimeBinding.inflate(inflater, container, false)
        val start = binding.startButton
        val startTime = binding.startTime
        val endTime = binding.endTime
        val backButton = binding.backButton
        val dataFromActivity = arguments?.getString("choose")
        backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
        start.setOnClickListener {
            if (isValidTime(startTime.text.toString(), endTime.text.toString())) {
                val bundle = Bundle().apply {
                    putString("startTime", startTime.text.toString())
                    putString("endTime", endTime.text.toString())
                }
                when (dataFromActivity) {
                    "run" -> {
                        val runFragment = RunFragment().apply {
                            arguments = bundle
                        }
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.set_time_layout, runFragment)
                            .addToBackStack(null)
                            .commit()
                    }

                    "walk" -> {
                        val walkFragment = WalkFragment().apply {
                            arguments = bundle
                        }
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.set_time_layout, walkFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                    else -> {
                        Toast.makeText(
                            requireContext(), "Please choose your exercise again !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(), "Invalid time !",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return binding.root
    }

    private fun isValidTime(timeStartStr: String, timeEndStr: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            sdf.isLenient = false
            val timeStartDate = sdf.parse(timeStartStr)
            val timeEndDate = sdf.parse(timeEndStr)
            timeStartDate != null && sdf.format(timeStartDate) == timeStartStr
                    && timeEndDate != null && sdf.format(timeEndDate) == timeEndStr
                    && timeEndDate.time - timeStartDate.time > 0
        } catch (e: Exception) {
            false
        }
    }
}

