package com.example.mycalcurator

class Calculator {

    fun addOperation(addOperation: AddOperation, num1: Double, num2: Double): Double = addOperation.operation(num1,num2)

    fun substractOperation(substractOperation: SubstractOperation, num1: Double, num2: Double): Double = substractOperation.operation(num1,num2)

    fun multiflyOperation(multiflyOperation: MultiflyOperation, num1: Double, num2: Double): Double = multiflyOperation.operation(num1,num2)

    fun divideOperation(divideOperation: DivideOperation, num1: Double, num2: Double): Double = divideOperation.operation(num1,num2)
    fun calculator(num1: Double, num2: Double, op: Int): Double {
        var result:Double = 0.0
        val addOperation = AddOperation()
        val substractOperation = SubstractOperation()
        val multiflyOperation = MultiflyOperation()
        val divideOperation = DivideOperation()

        when (op) {
            1 -> {result = addOperation(addOperation, num1, num2)}
            2 -> {result = substractOperation(substractOperation, num1, num2)}
            3 -> {result = multiflyOperation(multiflyOperation, num1, num2)}
            4 -> {result = divideOperation(divideOperation, num1, num2)}
        }
        return result
    }
}