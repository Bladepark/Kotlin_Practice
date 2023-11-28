package com.example.mycalcurator

class Calculator {
    fun calculator(num1: Int, num2: Int, op: Int): Int {
        var result = 0
        when (op) {
            1 -> {
                result = num1 + num2
            }

            2 -> {
                result = num1 - num2
            }

            3 -> {
                result = num1 * num2
            }

            4 -> {
                result = num1 / num2
            }

            else -> {
                result = -1
            }
        }

        return result
    }
}