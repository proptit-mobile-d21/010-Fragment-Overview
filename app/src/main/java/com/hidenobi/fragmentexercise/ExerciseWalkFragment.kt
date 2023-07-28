package com.hidenobi.fragmentexercise

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseRunBinding
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseWalkBinding
import java.sql.Date
import java.util.Locale


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseWalkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseWalkFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var min: Long = 0
    private var sec: Long = 0
    private var remainingTimeMillis: Long = 0
    private var secondSum : Long = 0
    private lateinit var binding: FragmentExerciseWalkBinding
    private lateinit var countDownTimer: CountDownTimer
    private var mIsPause : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExerciseWalkBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_walk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExerciseWalkBinding.bind(view)

        setFragmentResultListener("time_left") { requestKey, bundle ->
            min = bundle.getLong("hour") * 60 + bundle.getLong("min")
            binding.timeLeft.text = String.format("%02d:%02d", min, sec)
            binding.timeInsideProgressBar.text = String.format("%02d:%02d", min, sec)
            secondSum = min * 60 + sec
            remainingTimeMillis = secondSum * 1000
            binding.circleProgress.progress = 100
            startTimer()
        }

        binding.apply {
            binding.stopButton.setOnClickListener{
                if(mIsPause){
                    resumeTimer()
                    mIsPause = false
                }
                else{
                    pauseTimer()
                    mIsPause = true
                }
            }

            binding.dateInsideProgressBar.text = SimpleDateFormat("dd/MM").format(Calendar.getInstance().time)
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseWalkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ExerciseWalkFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    private fun startTimer(){
        countDownTimer = object : CountDownTimer(remainingTimeMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val minutesRemaining = millisUntilFinished / 1000 / 60
                val secondsRemaining = (millisUntilFinished / 1000) % 60
                remainingTimeMillis = millisUntilFinished
                binding.apply {
                    binding.timeLeft.text = String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                    binding.timeInsideProgressBar.text = String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                    binding.circleProgress.progress = ((millisUntilFinished.toFloat()/(secondSum * 1000).toFloat()) * 100).toInt()
                }

            }

            override fun onFinish() {
                Toast.makeText(context, "Timer end", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun pauseTimer(){
        countDownTimer.cancel()
    }

    private fun resumeTimer(){
        startTimer()
    }
}