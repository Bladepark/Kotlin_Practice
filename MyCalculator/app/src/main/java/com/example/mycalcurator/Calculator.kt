package com.example.mycalcurator

class Calculator {

    fun calculator(num1: Double, num2: Double, op: AbstractOperation): Double {
        return op.operation(num1,num2)
    }
}