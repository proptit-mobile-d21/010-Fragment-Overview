package com.hidenobi.fragmentexercise.fragment

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding
import com.hidenobi.fragmentexercise.model.Time

class SetTimeFragment : Fragment() {
    private var _binding: FragmentSetTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var exercise: Exercise
    private var startTime: Time = Time(0, 0)
    private var endTime: Time = Time(0, 1)

    companion object {
        fun newInstance(exercise: Exercise): Fragment {
            return SetTimeFragment().apply {
                this.exercise = exercise
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        binding.startInput.text = startTime.toString()
        binding.endInput.text = endTime.toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.startBtn.setOnClickListener {
            start()
        }
        binding.startInput.setOnClickListener {
            TimePickerDialog(context, { view, hourOfDay, minute ->
                startTime = Time(hourOfDay, minute)
                binding.startInput.text = startTime.toString()
            }, 0, 0, true).show()
        }
        binding.endInput.setOnClickListener {
            TimePickerDialog(context, { view, hourOfDay, minute ->
                endTime = Time(hourOfDay, minute)
                binding.endInput.text = endTime.toString()
            }, 0, 0, true).show()
        }
    }


    private fun start() {
        val counter = endTime - startTime
        if (counter.hour < 0) {
            Toast.makeText(
                context,
                "Thời gian kết thúc phải lớn hơn thời gian bắt đầu",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val exerciseFragment = ExerciseFragment.newInstance(exercise, counter)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.mainLayout, exerciseFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}