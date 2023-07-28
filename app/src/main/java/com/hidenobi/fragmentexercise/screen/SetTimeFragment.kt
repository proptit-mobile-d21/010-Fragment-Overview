package com.hidenobi.fragmentexercise.screen

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding
import com.hidenobi.fragmentexercise.modelView.FragmentViewModel
import com.hidenobi.fragmentexercise.section.Time

/**
 * A simple [Fragment] subclass.
 * Use the [SetTimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetTimeFragment : Fragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentSetTimeBinding
    private lateinit var btnBack: ImageView
    private lateinit var btnStart: TextView
    private lateinit var etStart: TextView
    private lateinit var etEnd: TextView
    private var hour: Int = 0
    private var min: Int = 0
    private val viewModel: FragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetTimeBinding.inflate(inflater, container, false)


        initComponent()
        setupClickListener()

        return binding.root
    }

    private fun initComponent() {
        btnBack = binding.btnBack
        btnStart = binding.btnStart
        etStart = binding.etStart
        etEnd = binding.etEnd
    }

    private fun setupClickListener() {
        val excerciseFragment = ExcerciseFragment()
        val timePickerDialog = TimePickerDialog(requireContext(), this, 0, 0, true)

        etStart.setOnClickListener {
//            timePickerDialog.setO
            viewModel.setCheckTime(true)
            timePickerDialog.show()
        }

        etEnd.setOnClickListener {
            val timePickerDialogEnd = TimePickerDialog(requireContext(), this, 0, 0, true)
            viewModel.setCheckTime(false)
            timePickerDialog.show()
        }

        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        btnStart.setOnClickListener {
            val startTime = etStart.text.toString()
            val endTime = etEnd.text.toString()
            if (Time.checkValidTime(startTime, endTime)) {
                viewModel.setStartTime(startTime)
                viewModel.setEndTime(endTime)
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, excerciseFragment)
                    addToBackStack(null)
                    commit()
                }
            } else {
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Invalid Time input!")
                    .setPositiveButton("OK", null)
                    .create()
                dialog.show()
            }
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if (viewModel.getCheckTime) {
            etStart.text =
                Time.convertToValideTime(hourOfDay) + ":" + Time.convertToValideTime(minute)
        } else {
            etEnd.text =
                Time.convertToValideTime(hourOfDay) + ":" + Time.convertToValideTime(minute)
        }

    }
}