package com.hidenobi.fragmentexercise.section

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
}