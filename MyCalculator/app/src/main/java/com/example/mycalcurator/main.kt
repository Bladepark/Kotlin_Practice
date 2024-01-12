package com.example.mycalcurator

import java.lang.NumberFormatException

//- Lv4 : AddOperation(더하기), SubtractOperation(빼기), MultiplyOperation(곱하기), DivideOperation(나누기) 연산 클래스들을
// AbstractOperation라는 클래스명으로 만들어 사용하여 추상화하고 Calculator 클래스의 내부 코드를 변경합니다.
//- Lv3 와 비교해서 어떠한 점이 개선 되었는지 스스로 생각해 봅니다.
//        - hint. 클래스간의 결합도, 의존성(의존성역전원칙)

fun main() {
    var num1: Double
    var num2: Double
    var op: AbstractOperation
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

fun isValidOperator() : AbstractOperation {
    while (true) {
        try {
            var op = readLine()!!.toInt()
            if (op in 1..4) {
                when(op) {
                    1 -> return AddOperation()
                    2 -> return SubstractOperation()
                    3 -> return MultiflyOperation()
                    4 -> return DivideOperation()
                }
            }
            else println("1부터 4까지 연산자에 해당하는 숫자를 입력하세요")
        } catch (e: NumberFormatException) {
            println("입력값 오류! 숫자를 입력해주세요.")
        }
    }
}