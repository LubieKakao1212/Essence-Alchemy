package com.LubieKakao1212.essencealchemy.util

fun linear(slope : Double, offset : Double = 0.0) : (Double) -> Double  {
    return {
        slope * it + offset
    }
}