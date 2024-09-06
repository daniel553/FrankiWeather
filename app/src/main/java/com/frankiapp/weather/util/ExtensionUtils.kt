package com.frankiapp.weather.util

import kotlin.math.roundToInt

fun Float.toFahrenheit(): Int {
    return (((9 / 5) * (this - 273)) + 32).roundToInt()
}