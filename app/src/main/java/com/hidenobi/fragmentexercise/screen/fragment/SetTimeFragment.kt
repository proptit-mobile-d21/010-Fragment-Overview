package com.hidenobi.fragmentexercise.screen.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding
import com.hidenobi.fragmentexercise.model.Time
import java.util.Calendar

typealias SetTimeOnFinish = (startTime: Time, endTime: Time) -> Unit

class SetTimeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(setTimeOnFinish: SetTimeOnFinish) =
            SetTimeFragment().apply {
                this.setTimeOnFinish = setTimeOnFinish
            }
    }

    private var _binding: FragmentSetTimeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var setTimeOnFinish: SetTimeOnFinish
    private lateinit var startTime: Time
    private lateinit var endTime: Time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calender: Calendar = Calendar.getInstance()
        startTime = Time(calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE))
        endTime = Time(startTime.hourOfDay, startTime.minute + 5)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetTimeBinding.inflate(layoutInflater, container, false)
        binding.setTimeStartTimeTxt.text = startTime.toString()
        binding.setTimeEndTimeTxt.text = endTime.toString()
        initBehaviour()
        return binding.root
    }

    private fun initBehaviour() {
        binding.setTimeBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.setTimeStartTimeTxt.setOnClickListener {
            val dialog = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    startTime.hourOfDay = hourOfDay
                    startTime.minute = minute
                    validEndTime()
                    binding.setTimeStartTimeTxt.text = startTime.toString()
                },
                startTime.hourOfDay,
                startTime.minute,
                true
            )
            dialog.show()
        }

        binding.setTimeEndTimeTxt.setOnClickListener {
            val dialog = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    endTime.hourOfDay = hourOfDay
                    endTime.minute = minute
                    validEndTime()
                    binding.setTimeEndTimeTxt.text = endTime.toString()
                },
                endTime.hourOfDay,
                endTime.minute,
                true
            )
            dialog.show()
        }

        binding.setTimeStartBtn.setOnClickListener { setTimeOnFinish.invoke(startTime, endTime) }
    }

    private fun validEndTime() {
        try {
            Time.timeBetween(startTime, endTime)
        } catch (e: Time.InvalidEndTime) {
            Toast.makeText(context, "Thời gian kết thúc không hợp lệ!", Toast.LENGTH_SHORT).show()
        }
        endTime.hourOfDay = startTime.hourOfDay
        endTime.minute = startTime.minute + 5
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}