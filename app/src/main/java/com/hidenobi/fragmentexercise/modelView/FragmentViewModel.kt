package com.hidenobi.fragmentexercise.modelView

import android.text.method.MultiTapKeyListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hidenobi.fragmentexercise.section.Time

class FragmentViewModel : ViewModel() {

    private var startTime: String = ""
    val getStartTime: String get() = startTime
    private var endTime: String = ""
    val getEndTime: String get() = endTime
    private var checkTime: Boolean = false
    val getCheckTime: Boolean get() = checkTime
    fun setStartTime(time: String){
        startTime = time
    }

    fun setEndTime(time: String){
        endTime = time
    }

    fun setCheckTime(res: Boolean){
        checkTime = res
    }

}