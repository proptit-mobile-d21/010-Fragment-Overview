package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding

class SetTimeFragment : Fragment() {
    private lateinit var binding: FragmentSetTimeBinding
    private lateinit var backBtn: ImageButton
    private lateinit var hourTxt: TextView
    private lateinit var minutesTxt:TextView
    private lateinit var startBtn: Button
    private lateinit var choose: String
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetTimeBinding.inflate(inflater,container,false)
        initComponent()
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        backBtn.setOnClickListener(){
            parentFragmentManager.popBackStack()
        }
        startBtn.setOnClickListener(){
           val timeleft = calculateTime()
            when(choose){
                "Run" ->{
                    val fragment: Fragment = RunFragment()
                    bundle.putInt("time",timeleft)
                    fragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, fragment)
                        addToBackStack(null)
                        commit()
                    }
                }
                "Walk"->{
                    val fragment: Fragment = WalkFragment()
                    bundle.putInt("time",timeleft)
                    fragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, fragment)
                        addToBackStack(null)
                        commit()
                    }
                }
            }
        }
    }

    private fun calculateTime(): Int {
        return hourTxt.text.toString().toInt() * 3600 + minutesTxt.text.toString().toInt()*60
    }

    private fun initComponent() {
        backBtn = binding.btnBack
        hourTxt = binding.editHour
        minutesTxt = binding.editMinutes
        startBtn = binding.btnStart
        choose = arguments!!.getString("choose").toString();
        bundle = Bundle()
    }
}