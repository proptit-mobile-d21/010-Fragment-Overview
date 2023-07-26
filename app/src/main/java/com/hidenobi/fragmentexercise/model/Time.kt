package com.hidenobi.fragmentexercise.model

import java.lang.Exception
import kotlin.math.min

class Time(hourOfDay: Int, minute: Int) {
    var hourOfDay: Int = 0
        set(value) { field = value % 24 }

    var minute: Int = 0
        set(value) {
            field = if (value >= 60) {
                hourOfDay += value / 60
                value % 60
            } else value
        }

    init {
        this.hourOfDay = hourOfDay
        this.minute = minute
    }

    fun toMinute(): Int = hourOfDay*60 + minute

    override fun toString(): String = "%02d:%02d".format(hourOfDay, minute)

    companion object {
        fun timeBetween(start: Time, end: Time) : Time {
            if (start.hourOfDay > end.hourOfDay)
                throw InvalidEndTime()
            else if (start.hourOfDay == end.hourOfDay && start.minute >= end.minute)
                throw InvalidEndTime()
            else
                return Time(end.hourOfDay - start.hourOfDay, end.minute - start.minute)
        }
    }

    class InvalidEndTime : Exception()
}