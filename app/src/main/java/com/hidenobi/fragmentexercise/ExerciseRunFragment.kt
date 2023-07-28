package com.hidenobi.fragmentexercise

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseRunBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseRunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseRunFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var min: Int = 0
    private var sec: Int = 0
    private lateinit var binding: FragmentExerciseRunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExerciseRunBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_run, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExerciseRunBinding.bind(view)

        setFragmentResultListener("time_left") { requestKey, bundle ->
            min = bundle.getInt("hour") * 60 + bundle.getInt("min")
            binding.timeLeft.text = String.format("%02d:%02d", min, sec)
            binding.circleProgress.progress = 100
            startTimer(min)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseRunFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ExerciseRunFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    private fun startTimer(minutes : Int){
        val secondSum = min * 60
        object : CountDownTimer((secondSum).toLong() * 1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val minutesRemaining = millisUntilFinished / 1000 / 60
                val secondsRemaining = (millisUntilFinished / 1000) % 60
                binding.apply {
                    binding.timeLeft.text =
                        String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                    binding.circleProgress.progress = ((millisUntilFinished.toFloat()/(secondSum.toLong() * 1000).toFloat()) * 100).toInt()
                }

            }

            override fun onFinish() {
                Toast.makeText(context, "Timer end", Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}