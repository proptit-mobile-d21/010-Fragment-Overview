package com.hidenobi.fragmentexercise.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {

    var startTime = MutableLiveData<String>()
    var endTime = MutableLiveData<String>()

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

}