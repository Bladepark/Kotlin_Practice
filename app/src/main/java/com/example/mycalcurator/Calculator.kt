package com.example.mycalcurator

class Calculator {
    fun calculator(num1: Double, num2: Double, op: Int): Double {
        var result:Double = 0.0
        when (op) {
            1 -> {result = num1 + num2}
            2 -> {result = num1 - num2}
            3 -> {result = num1 * num2}
            4 -> {result = num1 / num2}
            else -> {result = -1.0}
        }
        return result
    }
}