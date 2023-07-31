package com.hidenobi.fragmentexercise.fragment

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding
import java.time.Duration
import java.time.LocalTime
import java.util.Locale


class SetTimeFragment : Fragment() {

    private lateinit var binding: FragmentSetTimeBinding
    private lateinit var type: String
    private var countDownTime: Long = 0

    companion object {
        fun newInstance(data: String) = SetTimeFragment().apply {
            this.type = data
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.setTimeStart.setOnClickListener {
            setTime(binding.setTimeStart)
        }

        binding.setTimeEnd.setOnClickListener {
            setTime(binding.setTimeEnd)
        }

        binding.btnStart.setOnClickListener {
            if (!checkTime()) {
                Toast.makeText(context, "Invalid time!", Toast.LENGTH_SHORT).show()
            } else {
                val fragment = StartExerciseFragment.newInstance(type, countDownTime)
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(binding.layoutSetTime.id, fragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    private fun setTime(textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val is24Hour = true
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                textView.text =
                    String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
            },
            hour,
            min,
            is24Hour
        ).show()
    }

    private fun checkTime(): Boolean {
        val startTimeText = binding.setTimeStart.text.toString().trim()
        val endTimeText = binding.setTimeEnd.text.toString().trim()
        if (startTimeText.isEmpty() || endTimeText.isEmpty())
            return false
        val startTime = LocalTime.parse(startTimeText)
        val endTime = LocalTime.parse(endTimeText)
        countDownTime = Duration.between(startTime, endTime).toMillis()
        return !startTime.isAfter(endTime)
    }
}