package com.hidenobi.fragmentexercise.fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.Type


class SetTimeFragment : Fragment() {
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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_time, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgBack = view.findViewById<ImageView>(R.id.imgBack)

        val choice = arguments?.getString("choice")
      //  Toast.makeText(context, "$choice", Toast.LENGTH_SHORT).show()

        imgBack?.setOnClickListener {
            Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }



        val edtStart = view.findViewById<EditText>(R.id.edtStart)
        edtStart?.setOnClickListener {
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    val hour = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
                    val min = if (minute < 10) "0$minute" else "$minute"
                    edtStart.setText("$hour:$min")
                },
                0,
                0,

                true
            ).show()
        }

        val edtEnd = view.findViewById<EditText>(R.id.edtEnd)
        edtEnd?.setOnClickListener {
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    val hour = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
                    val min = if (minute < 10) "0$minute" else "$minute"
                    edtEnd.setText("$hour:$min")
                },
                0,
                0,

                true
            ).show()
        }

        val btnStart = view.findViewById<Button>(R.id.btnStart)
        btnStart?.setOnClickListener {
            val start = edtStart?.text.toString()
            val end = edtEnd?.text.toString()
            if (start.isEmpty() || end.isEmpty()) {
                Toast.makeText(context, "Please enter start and end time", Toast.LENGTH_SHORT)
                    .show()
            } else if (start >= end) {
                Toast.makeText(context, "Start time must be before end time", Toast.LENGTH_SHORT)
                    .show()
            } else {
               // val intent = Intent(context, com.hidenobi.fragmentexercise.ExerciseActivity::class.java)
                val time = (end.substring(0,2).toInt() - start.substring(0,2).toInt())*60*60 + (end.substring(3,5).toInt()*60 - start.substring(3,5).toInt()*60)
               // intent.putExtra("time", time)
                val fragment = RunAndWalkFragment.newInstance(time, Type.valueOf(choice!!))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack("RunFragment")
                    .commit()

               // startActivity(intent)
            }
        }

    }


}