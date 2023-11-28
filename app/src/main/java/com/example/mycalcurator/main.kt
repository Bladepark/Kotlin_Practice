package com.example.mycalcurator

fun main() {
    println("-------------------계산기-------------------")
    println("--------연산할 첫 번째 숫자를 입력하세요--------")
    val num1 = readLine()!!.toDouble()
    println("--------연산할 두 번째 숫자를 입력하세요--------")
    val num2 = readLine()!!.toDouble()
    println("-------연산자에 해당하는 숫자를 입력하세요-------")
    println("-----1(더하기) 2(빼기) 3(곱하기) 4(나누기)-----")
    val op = readLine()!!.toInt()

    var cal = Calculator()
    var result = cal.calculator(num1, num2, op)
    println("--------------연산 결과 : ${result} --------------")
}

