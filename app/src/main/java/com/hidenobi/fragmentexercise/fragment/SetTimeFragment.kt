package com.hidenobi.fragmentexercise.fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.Type
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding


class SetTimeFragment : Fragment() {
    private lateinit var binding : FragmentSetTimeBinding
    companion object{
        fun newInstance(type : Type) = SetTimeFragment().apply {
            arguments = Bundle().apply {
                putString("choice", type.name)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val choice = arguments?.getString("choice")

        binding.imgBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.edtStart.setOnClickListener {
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    val hour = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
                    val min = if (minute < 10) "0$minute" else "$minute"
                    binding.edtStart.setText("$hour:$min")
                },
                0,
                0,

                true
            ).show()
        }


        binding.edtEnd.setOnClickListener {
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    val hour = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
                    val min = if (minute < 10) "0$minute" else "$minute"
                    binding.edtEnd.setText("$hour:$min")
                },
                0,
                0,

                true
            ).show()
        }


        binding.btnStart.setOnClickListener {
            val start = binding.edtStart.text.toString()
            val end = binding.edtEnd.text.toString()
            if (start.isEmpty() || end.isEmpty()) {
                Toast.makeText(context, "Please enter start and end time", Toast.LENGTH_SHORT)
                    .show()
            } else if (start >= end) {
                Toast.makeText(context, "Start time must be before end time", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val time = (end.substring(0,2).toInt() - start.substring(0,2).toInt())*60*60 + (end.substring(3,5).toInt()*60 - start.substring(3,5).toInt()*60)
                val fragment = RunAndWalkFragment.newInstance(time, Type.valueOf(choice!!))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack("RunFragment")
                    .commit()
            }
        }

    }


}