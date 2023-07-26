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

        val dataText = arguments?.getString("action").toString()
        var dataTime : Long
        val exerciseFragment = ExerciseFragment()
        val bundle = Bundle()

        binding.check.setOnClickListener {
            if(binding.startTime.text.toString().isEmpty() || binding.endTime.text.toString().isEmpty()){
                Toast.makeText(context, "Vui lòng nhập thời gian", Toast.LENGTH_SHORT).show()
            }
            else {
                check()
            }
        }
        binding.start.setOnClickListener {
            if(binding.startTime.text.toString().isEmpty() || binding.endTime.text.toString().isEmpty()){
                Toast.makeText(context, "Vui lòng nhập thời gian", Toast.LENGTH_SHORT).show()
            }
            else {
                check()
                dataTime = calculateDifference()
                if(dataTime < 0){
                    Toast.makeText(context, "Vui lòng nhập lại thời gian", Toast.LENGTH_SHORT).show()
                }
                else{
                    bundle.putString("action2","$dataText")
                    bundle.putLong("action3", dataTime)
                    exerciseFragment.arguments = bundle

                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(binding.fragmentSetTime.id, exerciseFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    private fun calculateDifference(): Long {
        val startTimeStr = binding.startTime.text.toString()
        val endTimeStr = binding.endTime.text.toString()

        val timeFormat = SimpleDateFormat("mm:ss", Locale.getDefault())

        val timeLeft = timeFormat.parse(endTimeStr).time - timeFormat.parse(startTimeStr).time
        return timeLeft/1000
    }

    private fun check(){
        val format = SimpleDateFormat("mm:ss", Locale.getDefault())
        val start = format.parse(binding.startTime.text.toString()).time
        val startStr = format.format(Date(start))
        binding.startTime.setText(startStr.toString())

        val end = format.parse(binding.endTime.text.toString()).time
        val endStr= format.format(Date(end))
        binding.endTime.setText(endStr.toString())
    }
}