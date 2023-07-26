package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    private var countDownTimer: CountDownTimer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataText = arguments?.getString("action2")
        binding.textAct.setText(dataText.toString())
        val dataTime = arguments?.getLong("action3")
        var dataMM = dataTime?.div(60)
        var dataSS = dataTime?.minus(dataMM?.times(60)!!)
        var dataMmStr = dataMM.toString()
        var dataSsStr = dataSS.toString()
        if(dataMmStr.length.equals(1)) {
            dataMmStr = "0" + dataMmStr
        }
        if(dataSsStr.length.equals(1)) {
            dataSsStr = "0" + dataSsStr
        }
        binding.textTime.text = dataMmStr + " : " + dataSsStr

        binding.pause.setOnClickListener {
            val inputSeconds = dataTime!!
            val totalMilliseconds = inputSeconds * 1000
            binding.textTime.setText("$dataTime")
            countDownTimer = object : CountDownTimer(totalMilliseconds, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    dataMM = secondsRemaining / 60
                    dataSS = secondsRemaining - dataMM!! * 60
                    dataMmStr = dataMM.toString()
                    dataSsStr = dataSS.toString()
                    if(dataMmStr.length.equals(1)) {
                        dataMmStr = "0" + dataMmStr
                    }
                    if(dataSsStr.length.equals(1)) {
                        dataSsStr = "0" + dataSsStr
                    }
                    binding.textTime.text = dataMmStr + " : " + dataSsStr
                }
                override fun onFinish() {
                    Toast.makeText(context, "Hoàn thành",Toast.LENGTH_SHORT ).show()
                }
            }.start()
        }

    }
}