package com.hidenobi.fragmentexercise.model

class Exercise {
    enum class Type {Walk, Run}
    var type: Exercise.Type? = null
    var startTime: Time? = null
    var endTime: Time? = null
}
