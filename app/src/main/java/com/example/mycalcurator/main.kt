package com.example.mycalcurator

import java.lang.NumberFormatException

//Lv3 : AddOperation(더하기), SubstractOperation(빼기), MultiplyOperation(곱하기), DivideOperation(나누기) 연산 클래스를 만든 후 클래스간의 관계를 고려하여 Calculator 클래스와 관계를 맺기
// 입력값 안정성 높이기 -> 완료

fun main() {
    var num1: Double
    var num2: Double
    var op: Int
    var keepOrExit: Int

    println("-------------------계산기-------------------")
    println("--------연산할 첫 번째 숫자를 입력하세요--------")
    num1 = isNumber()
    println("-------연산자에 해당하는 숫자를 입력하세요-------")
    println("-----1(더하기) 2(빼기) 3(곱하기) 4(나누기)-----")
    op = isValidOperator()
    println("--------연산할 두 번째 숫자를 입력하세요--------")
    num2 = isNumber()

    val cal = Calculator()
    var result = cal.calculator(num1, num2, op)
    println("-----------연산 결과 : ${result} -----------")

    while (true) {
        println("-이어서 연산 하시려면 1 종료하시려면 2 입력해주세요-")
        while (true) {
            try {
                keepOrExit = readLine()!!.toInt()
                if (keepOrExit == 1) break
                else if (keepOrExit == 2) {
                    println("---------------계산기 종료----------------")
                    return
                }
                else println("입력값 오류! 이어서 연산하려면 1 종료하려면 2를 입력해주세요")
            } catch (e: NumberFormatException) {
                println("입력값 오류! 숫자를 입력해주세요. 1 -> 계속 2 -> 종료")
            }
        }
        println("-------연산자에 해당하는 숫자를 입력하세요-------")
        println("-----1(더하기) 2(빼기) 3(곱하기) 4(나누기)-----")
        op = isValidOperator()
        println("--------연산할 두 번째 숫자를 입력하세요--------")
        num2 = isNumber()
        result = cal.calculator(result, num2, op)
        println("--------------연산 결과 : ${result} --------------")
    }
}

fun isNumber() : Double{
    while (true) {
        try {
            var num = readLine()!!.toDouble()
            return num
        } catch (e: NumberFormatException) {
            println("입력값 오류! 숫자를 입력해주세요.")
        }
    }
}

fun isValidOperator() : Int {
    while (true) {
        try {
            var op = readLine()!!.toInt()
            if (op in 1..4) return op
            else println("1부터 4까지 연산자에 해당하는 숫자를 입력하세요")
        } catch (e: NumberFormatException) {
            println("입력값 오류! 숫자를 입력해주세요.")
        }
    }
}