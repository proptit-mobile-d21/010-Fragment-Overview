package com.hidenobi.fragmentexercise.screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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
class SetTimeFragment : Fragment() {
    private lateinit var binding: FragmentSetTimeBinding
    private lateinit var btnBack: ImageView
    private lateinit var btnStart: TextView
    private lateinit var etStart: EditText
    private lateinit var etEnd: EditText
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
        val setTimeFragment = SetTimeFragment()
        val excerciseFragment = ExcerciseFragment()

        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        btnStart.setOnClickListener {
            val startTime = etStart.text.toString()
            val endTime = etEnd.text.toString()
            if(Time.checkValidTime(startTime, endTime)){
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, excerciseFragment)
                    addToBackStack(null)
                    commit()
                }
            }else{
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Invalid Time input!")
                    .setPositiveButton("OK", null)
                    .create()
                dialog.show()
            }
        }
    }
}