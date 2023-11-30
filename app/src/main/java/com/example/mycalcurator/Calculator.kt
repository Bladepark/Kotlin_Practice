package com.example.mycalcurator

class Calculator {

    fun calculator(num1: Double, num2: Double, op: Int): Double {
        var result:Double = 0.0
        val addOperation = AddOperation()
        val substractOperation = SubstractOperation()
        val multiflyOperation = MultiflyOperation()
        val divideOperation = DivideOperation()

        when (op) {
            1 -> {result = addOperation.operation(num1,num2)}
            2 -> {result = substractOperation.operation(num1,num2)}
            3 -> {result = multiflyOperation.operation(num1,num2)}
            4 -> {result = divideOperation.operation(num1,num2)}
        }
        return result
    }
}