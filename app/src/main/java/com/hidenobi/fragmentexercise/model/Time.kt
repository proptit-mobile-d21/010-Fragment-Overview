package com.hidenobi.fragmentexercise.model

import android.util.Log

class Time(val hour: Int, val minute: Int) {
    override fun toString(): String {
        return String.format("%02d:%02d", hour, minute)
    }

    operator fun minus(other: Time): Time {
        var m = minute - other.minute
        var h = hour - other.hour
        if (m < 0) {
            m += 60
            h -= 1
        }
        return Time(h, m)
    }
}