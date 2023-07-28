package com.hidenobi.fragmentexercise.utils

object FormatTime {
    fun hourAndMinuteToMillis(hour: Int, minute: Int): Long {
        return ((hour * 60 + minute) * 60 * 1000).toLong()
    }

    fun millisToHourAndMinute(millis: Long): String {
        val h = ((millis / 1000) / 60) / 60
        val m = ((millis / 1000) / 60) % 60
        return "%02d:%02d".format(h, m)
    }

    fun millisToHourMinuteAndSecond(millis: Long): String {
        val h = ((millis / 1000) / 60) / 60
        val m = ((millis / 1000) / 60) % 60
        val s = (millis / 1000) % 60
        return "%02d:%02d:%02d".format(h, m, s)
    }
}