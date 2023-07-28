package com.hidenobi.fragmentexercise.section

import android.util.Log
import android.widget.Toast
import java.time.LocalTime
import java.util.regex.Pattern

object Time {

    fun checkValidTime(time: String) : Boolean{
        val regex = "\\d{2}:\\d{2}"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(time)
        return matcher.matches()
    }

    fun checkValidTime(start: String, end: String): Boolean{
        if(!checkValidTime(start) || !checkValidTime(end)) return false
        val sTime = LocalTime.parse(start)
        val eTime = LocalTime.parse(end)
        return sTime.isBefore(eTime)
    }

    fun convertToValideTime(time: Int): String{
        var resTime = time.toString()
        if(resTime.length == 1){
            resTime = "0" + resTime
        }
        return resTime
    }

    fun minusTime(start: LocalTime, end: LocalTime): LocalTime {
        var sTime = start
        var eTime = end
        eTime = eTime.minusHours(sTime.hour.toLong())
        eTime = eTime.minusMinutes(sTime.minute.toLong())
        Log.d("Checkingg", sTime.hour.toString())
        Log.d("Checkingg", sTime.toString())
        Log.d("Checkingg", eTime.hour.toString())
        Log.d("Checkingg", eTime.toString())
        return eTime
    }

    fun convertToMillisec(time: LocalTime): Int {
        val min = time.minute
        val sec = time.second
        val millisec: Int = min*60000 + sec*1000
        return millisec
    }


}