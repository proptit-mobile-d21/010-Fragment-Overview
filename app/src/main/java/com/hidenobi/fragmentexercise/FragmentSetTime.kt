package com.hidenobi.fragmentexercise

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding
import java.text.SimpleDateFormat
import java.util.Date


class FragmentSetTime : Fragment() {

    private lateinit var view: View
    private var _binding: FragmentSetTimeBinding? = null
    private val binding get() = _binding!!
    private var start_hour: Int = 0
    private var start_min: Int = 0
    private var end_hour: Int = 0
    private var end_min: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        view = binding.root

        binding.btnstart.setOnClickListener {
            val nextFr = FragmentExercise()
            val fm: FragmentTransaction? =
                requireActivity().supportFragmentManager?.beginTransaction()
            fm?.replace(R.id.framelayout1, nextFr)?.addToBackStack("Exercise")?.commit()
        }
        val toolbar = binding.timetoolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = "Time"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val timestart = binding.Timestart
        val timeend = binding.Timeend

        val cal = Calendar.getInstance()
        val currentTime = cal.time
        val fm = SimpleDateFormat("HH:mm")
        val formattedTime = fm.format(currentTime)
        timestart.text = formattedTime
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        cal.set(Calendar.MINUTE, m + 10)
        cal.set(Calendar.HOUR_OF_DAY, h)
        val formattedTime1 = fm.format(cal.time)
        timeend.text = formattedTime1
        start_hour = h
        start_min = m
        end_hour = h
        end_min = m + 10


        timestart.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hours = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(context,
                TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    val c = Calendar.getInstance()
                    c.set(Calendar.HOUR_OF_DAY, hour)
                    c.set(Calendar.MINUTE, minute)
                    val format = SimpleDateFormat("HH:mm")
                    val time: String = format.format(c.time)

                    start_hour = hour
                    start_min = minute
                    validateTimeEnd()
                    timestart.text = time
                }, hours, minutes, true
            )
            timePickerDialog.show()
        }

        timeend.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hours = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    val c = Calendar.getInstance()

                    end_hour = hour
                    end_min = minute
                    validateTimeEnd()
                    c.set(Calendar.HOUR_OF_DAY, end_hour)
                    c.set(Calendar.MINUTE, end_min)
                    val format = SimpleDateFormat("HH:mm")
                    val time = format.format(c.time)
                    timeend.text = time
                },
                hours,
                minutes,
                true
            )
            timePickerDialog.show()
        }



        return view

    }

    private fun validateTimeEnd() {
        if (start_hour > end_hour || (start_hour == end_hour && start_min > end_min - 5)) {
            Toast.makeText(context, "Time for exercise is at least 5 minutes", Toast.LENGTH_SHORT)
                .show()
            end_hour = start_hour
            end_min = start_min + 5
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}