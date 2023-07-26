package com.hidenobi.fragmentexercise.modelView

import android.text.method.MultiTapKeyListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hidenobi.fragmentexercise.section.Time

class FragmentViewModel : ViewModel() {

    var startTime = MutableLiveData<String>()
    var endTime = MutableLiveData<String>()
    var checkTime = MutableLiveData<Boolean>()
    var minusTime = Time.minusTime(getStartTime(), getEndTime())

    fun setStartTime(time: String){
        startTime.value = time
    }

    fun getStartTime(): String? {
        return startTime.value
    }

    fun setEndTime(time: String){
        endTime.value = time
    }

    fun getEndTime(): String? {
        return endTime.value
    }

    fun setCheckTime(res: Boolean){
        checkTime.value = res
    }

    fun getCheckTime(): Boolean?{
        return checkTime.value
    }
}